package com.mindtree.EMandi.modules.crop.converter;

import org.modelmapper.ModelMapper;

import com.mindtree.EMandi.modules.crop.dto.CropVarietyDto;
import com.mindtree.EMandi.modules.crop.entity.CropVariety;

public class CropVarietyConverter {
	public CropVarietyDto entityToDto(CropVariety crop) {
		
		ModelMapper mapper = new ModelMapper();
		CropVarietyDto cropVarietyDto = mapper.map(crop, CropVarietyDto.class);
		return cropVarietyDto;
	}
}