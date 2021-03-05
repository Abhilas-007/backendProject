package com.mindtree.EMandi.modules.crop.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mindtree.EMandi.modules.crop.dto.CropDto;
import com.mindtree.EMandi.modules.crop.entity.Crop;

@Component
public class CropConverter 
{
	public CropDto entityToDto(Crop crop)
	{
		CropDto dto = new CropDto();
		dto.setCropName(crop.getCropName());
		dto.setCropMSP(crop.getCropMSP());
		dto.setAdminId(crop.getAdmin().getAdminId());
		return dto;
	}
	
	public List<CropDto> entityToDto(List<Crop> crops)
	{
		return crops.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}
}
