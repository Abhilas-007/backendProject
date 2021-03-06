package com.mindtree.EMandi.modules.crop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.EMandi.modules.crop.converter.CropConverter;
import com.mindtree.EMandi.modules.crop.dto.CropDto;
import com.mindtree.EMandi.modules.crop.entity.Crop;
import com.mindtree.EMandi.modules.crop.repository.CropRepository;
import com.mindtree.EMandi.modules.crop.service.CropService;

@Service
public class CropServiceImpl implements CropService {
	@Autowired
	private CropRepository cropRepo;
	@Autowired
	private CropConverter cropConverter;

	@Override
	public String addCrop(CropDto cropDto) {
		Crop crop = cropConverter.dtoToEntity(cropDto);
		cropRepo.save(crop);
		return "Successfully added crop";
	}

	@Override
	public List<CropDto> getAllCrops() {
		List<Crop> crops = cropRepo.findAll();
		return cropConverter.entityToDtoForList(crops);
	}

}
