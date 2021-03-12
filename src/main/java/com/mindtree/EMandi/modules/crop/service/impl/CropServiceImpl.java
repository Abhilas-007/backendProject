package com.mindtree.EMandi.modules.crop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.EMandi.exception.DataNotAddedException;
import com.mindtree.EMandi.exception.DatabaseConnectionFailureException;
import com.mindtree.EMandi.exception.ServiceException;
import com.mindtree.EMandi.modules.crop.entity.Crop;
import com.mindtree.EMandi.modules.crop.entity.CropVariety;
import com.mindtree.EMandi.modules.crop.repository.CropRepository;
import com.mindtree.EMandi.modules.crop.repository.CropVarietyRepository;
import com.mindtree.EMandi.modules.crop.service.CropService;

@Service
public class CropServiceImpl implements CropService {
	@Autowired
	private CropRepository cropRepo;

	@Autowired
	private CropVarietyRepository cropVarietyRepo;

	@Override
	public String addCrop(Crop crop) {
		cropRepo.save(crop);
		return "Successfully added crop";
	}

	@Override
	public List<Crop> getAllCrops() {
		return cropRepo.findAll();
	}

	@Override
	public Crop getCropMSP(String cropName, String adminId) {
		// TODO Auto-generated method stub
		Crop mspCrop = cropRepo.findMSP(cropName, adminId);
		return mspCrop;
	}

	@Override
	public String updateMSP(Crop crop) {
		// TODO Auto-generated method stub
		Crop originalCrop = cropRepo.findMSP(crop.getCropName(), crop.getAdmin().getAdminId());
		crop.setCropId(originalCrop.getCropId());
		cropRepo.save(crop);
		return "Successfuly updated";
	}

	@Override
	public CropVariety getCropCostForBuyer(String cropName, String cropClass, String adminId) {
		Crop crop = null;
		try {
			crop = cropRepo.findMSP(cropName, adminId);
		} catch (Exception e) {
			return null;
		}
		int cropId = crop.getCropId();
		try {
			CropVariety cropPrice = cropVarietyRepo.getBuyerCropPrice(cropId, cropClass);
			return cropPrice;
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public CropVariety updateCropCostForBuyer(String cropName, String cropClass, String cropPrice, String adminId) {

		Crop crop = cropRepo.findMSP(cropName, adminId);
		CropVariety cropVariety = null;
		if (crop != null) {
			int cropId = crop.getCropId();
			cropVariety = cropVarietyRepo.getBuyerCropPrice(cropId, cropClass);
			if (cropVariety != null) {
				cropVariety.setBuyerCropPrice(Double.parseDouble(cropPrice));
				cropVariety.setCrop(crop);
				cropVariety.setCropClass(cropClass);
				cropVariety = cropVarietyRepo.save(cropVariety);
			}

		}
		return cropVariety;
	}

	@Override
	public List<Crop> findCropByAdminId(String adminId) {
		// TODO Auto-generated method stub
		List<Crop> crop = cropRepo.findByAdminId(adminId);
		return crop;
	}

}
