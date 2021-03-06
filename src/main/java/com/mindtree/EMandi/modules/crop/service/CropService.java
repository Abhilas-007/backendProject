package com.mindtree.EMandi.modules.crop.service;

import java.util.List;

import com.mindtree.EMandi.modules.crop.entity.Crop;

public interface CropService {
	public String addCrop(Crop crop);

	public List<Crop> getAllCrops();
}
