package com.mindtree.EMandi.modules.crop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.EMandi.modules.crop.entity.Crop;
import com.mindtree.EMandi.modules.crop.repository.CropRepository;
import com.mindtree.EMandi.modules.crop.service.CropService;

@Service
public class CropServiceImpl implements CropService
{
	@Autowired private CropRepository cropRepo;

	@Override
	public String addCrop(Crop crop) 
	{
		cropRepo.save(crop);
		return "Successfully added crop";
	}

	@Override
	public List<Crop> getAllCrops() 
	{
		return cropRepo.findAll();
	}

}
