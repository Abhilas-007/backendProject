package com.mindtree.EMandi.modules.crop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Crop getCropMSP(Crop crop) {
		// TODO Auto-generated method stub
		Crop mspCrop = cropRepo.findMSP(crop.getCropName(), crop.getAdmin().getAdminId());
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
		Crop crop = cropRepo.findMSP(cropName, adminId);
		int cropId = crop.getCropId();
		return cropVarietyRepo.getBuyerCropPrice(cropId, cropClass);

	}

}
