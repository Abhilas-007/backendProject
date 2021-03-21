package com.mindtree.EMandi.modules.clerk.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.mindtree.EMandi.exception.ServiceException;
import com.mindtree.EMandi.modules.clerk.dto.ClerkCropDto;
import com.mindtree.EMandi.modules.clerk.entity.Clerk;
import com.mindtree.EMandi.modules.clerk.repository.ClerkRepository;
import com.mindtree.EMandi.modules.clerk.service.ClerkService;
import com.mindtree.EMandi.modules.crop.dto.CropNameQtyDto;
import com.mindtree.EMandi.modules.crop.repository.CropRepository;
import com.mindtree.EMandi.modules.farmer.converter.FarmerConverter;
import com.mindtree.EMandi.modules.farmer.dto.FarmerTransactionDto;
import com.mindtree.EMandi.modules.farmer.entity.Farmer;
import com.mindtree.EMandi.modules.farmer.entity.FarmerTransaction;
import com.mindtree.EMandi.modules.farmer.repository.FarmerTransactionRepository;
import com.mindtree.EMandi.modules.farmer.service.FarmerService;
import com.mindtree.EMandi.modules.mandi.repository.MandiRepository;
import com.mindtree.EMandi.modules.mandi.service.MandiService;

@Service
public class ClerkServiceImpl implements ClerkService {

	@Autowired
	ClerkRepository clerkRepo;

	@Autowired
	private MandiRepository mandiRepo;

	@Autowired
	private CropRepository cropRepo;

	@Autowired
	private FarmerConverter farmerConverter;

	@Autowired
	private FarmerTransactionRepository farmerTransactionRepo;

	@Autowired
	private MandiService mandiService;

	@Autowired
	private FarmerService farmerService;

	@Autowired
	SpringTemplateEngine tempEngine;
	@Autowired
	private JavaMailSender sender;

	@Override
	public String validateLogin(Map<String, String> map) {
		Clerk clerk = clerkRepo.findById(map.get("userId")).orElse(null);
		if (clerk != null)
			if (clerk.getPassword().equals(map.get("password")))
				return map.get("userId");
			else
				return null;
		return null;
	}

	@Override
	public Clerk getClerk(String id) throws ServiceException {
		Clerk clerk;
		try {
			clerk = clerkRepo.findById(id).get();
		} catch (IllegalArgumentException e) {
			throw new ServiceException("No data found for that id", e);
		}

		return clerk;
	}

	@Override
	public Clerk updatePassword(Map<String, String> map) throws ServiceException {
		String id = map.get("userId");
		Clerk clerk;
		try {
			clerk = clerkRepo.findById(id).get();
			clerk.setPassword(map.get("password"));
			clerkRepo.save(clerk);
		} catch (IllegalArgumentException e) {
			throw new ServiceException("Password couldnt be updated");
		}
		return clerk;
	}

	@Override
	public double getTotalPrice(ClerkCropDto clerkCropDto[]) {
		String adminId = mandiRepo.findAdminIdByClerkId(clerkCropDto[0].getClerkId());

		double total = 0;
		List<CropNameQtyDto> itemList = new ArrayList<CropNameQtyDto>();

		for (int i = 0; i < clerkCropDto.length; i++) {
			CropNameQtyDto tempCropItem = new CropNameQtyDto();
			tempCropItem.setCropName(clerkCropDto[i].getCropName());
			tempCropItem.setCropQty(clerkCropDto[i].getCropQty());
			itemList.add(tempCropItem);
		}

		for (CropNameQtyDto c : itemList) {
			double cost = 0;
			try {
				cost = (cropRepo.findMSP(c.getCropName(), adminId)).getCropMSP();
			} catch (Exception e) {
				cost = 0;
			}
			total += ((cost) * (c.getCropQty()));
		}
		return total;
	}

	@Override
	public double buyCrops(ClerkCropDto clerkCropDto[]) {
		double totalAmount = 0;
		double totalStorage = 0;
		int mandiPincode = clerkRepo.findMandiPincodeByClerkId(clerkCropDto[0].getClerkId());
		String adminId = mandiRepo.findAdminIdByClerkId(clerkCropDto[0].getClerkId());
		System.out.println(mandiPincode);
		System.out.println(adminId);

		List<CropNameQtyDto> itemList = new ArrayList<CropNameQtyDto>();

		for (int i = 0; i < clerkCropDto.length; i++) {
			CropNameQtyDto tempCropItem = new CropNameQtyDto();
			tempCropItem.setCropName(clerkCropDto[i].getCropName());
			tempCropItem.setCropQty(clerkCropDto[i].getCropQty());
			itemList.add(tempCropItem);
		}

		for (CropNameQtyDto c : itemList) {
			double cost = 0;
			try {
				cost = (cropRepo.findMSP(c.getCropName(), adminId)).getCropMSP();
			} catch (Exception e) {
				cost = 0;
			}
			totalAmount += ((cost) * (c.getCropQty()));
			if (cost != 0) {
				totalStorage += c.getCropQty();
			}

			FarmerTransactionDto farmerTransDto = new FarmerTransactionDto();
			farmerTransDto.setAmount((cost) * (c.getCropQty()));
			farmerTransDto.setCropClass("C");
			farmerTransDto.setCropName(c.getCropName());
			farmerTransDto.setFarmerId(clerkCropDto[0].getFarmerId());
			farmerTransDto.setMandiPincode(mandiPincode);
			farmerTransDto.setQuantity(c.getCropQty());

			FarmerTransaction farmerTrans = new FarmerTransaction();
			farmerTrans = farmerConverter.dtoToEntityTrans(farmerTransDto);
			if (farmerTrans.getAmount() != 0) {
				farmerTransactionRepo.save(farmerTrans);
			}
		}

		// subtract total storage required from mandi storage
		mandiService.updateMandiStorage(mandiPincode, totalStorage);

		return totalAmount;
	}

	@Override
	public double getStorageByClerkId(String clerkId) {
		int mandiPincode = clerkRepo.findMandiPincodeByClerkId(clerkId);
		double storage = mandiRepo.findById(mandiPincode).get().getStorage();
		return storage;
	}

	@Override
	public double getSingleCropPrice(ClerkCropDto clerkCropDto) {
		String adminId = mandiRepo.findAdminIdByClerkId(clerkCropDto.getClerkId());
		double cost = 0;
		try {
			cost = (cropRepo.findMSP(clerkCropDto.getCropName(), adminId)).getCropMSP();
		} catch (Exception e) {
			cost = 0;
		}
		return cost;
	}

	@Override
	public boolean validateFarmerId(int farmerId) {
		Farmer farmer = new Farmer();
		try {
			farmer = farmerService.getFarmer(farmerId);
		} catch (Exception e) {
			farmer = null;
		}

		if (farmer == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String passwordMail(Map<String, String> map) throws ServiceException {
		MimeMessage message = sender.createMimeMessage();
		try {
			Clerk clerk = clerkRepo.findById(map.get("userId")).get();
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			Map<String, Object> model = new HashMap<>();
			model.put("password", clerk.getPassword());

			Context context = new Context();
			context.setVariables(model);
			String htmlPage = tempEngine.process("passwordTemp", context);
			helper.setTo(clerk.getEmailId());
			helper.setText(htmlPage, true);
			helper.setSubject("Password for logging into the system");
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
		sender.send(message);
		return "sent mail";
	}

	@Override
	public List<Integer> getFarmerIds(String clerkId) {
		// TODO Auto-generated method stub
		int mandiPincode = mandiRepo.getMandiPincode(clerkId);
		
		return farmerTransactionRepo.getFarmerIds(mandiPincode);
	}
}
