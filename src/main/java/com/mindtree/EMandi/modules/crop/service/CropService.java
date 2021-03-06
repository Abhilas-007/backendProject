package com.mindtree.EMandi.modules.crop.service;

import java.util.List;

import com.mindtree.EMandi.modules.crop.dto.CropDto;

public interface CropService {
	public String addCrop(CropDto cropDto);

	public List<CropDto> getAllCrops();
}
