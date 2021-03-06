package com.mindtree.EMandi.modules.crop.service;

import java.util.List;

import com.mindtree.EMandi.modules.crop.entity.Crop;
import com.mindtree.EMandi.modules.crop.entity.CropVariety;

public interface CropService {
	public String addCrop(Crop crop);

	public List<Crop> getAllCrops();
	
	public Crop getCropMSP(Crop crop);
	
	public String updateMSP(Crop crop);
	
	public CropVariety getCropCostForBuyer(String cropName, String cropClass, String adminId);

	public CropVariety updateCropCostForBuyer(String cropName, String cropClass, String cropPrice, String adminId);
}
