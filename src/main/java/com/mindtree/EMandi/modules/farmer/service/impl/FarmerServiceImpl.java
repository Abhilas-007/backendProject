package com.mindtree.EMandi.modules.farmer.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.EMandi.exception.DataNotAddedException;
import com.mindtree.EMandi.modules.farmer.entity.Farmer;
import com.mindtree.EMandi.modules.farmer.repository.FarmerRepository;
import com.mindtree.EMandi.modules.farmer.service.FarmerService;

@Service
public class FarmerServiceImpl implements FarmerService{

	@Autowired
	FarmerRepository farmerRepo;

	@Override
	public String validateLogin(Map<String, String> map) {
		Farmer farmer = farmerRepo.findById(map.get("userId")).orElse(null);
		if (farmer != null)
			if (farmer.getPassword().equals(map.get("password")))
				return map.get("userId");
			else
				return null;
		return null;
	}
	@Override
	public Farmer createFarmer(Farmer farmer) throws DataNotAddedException {

		try {
			return farmerRepo.save(farmer);

		} catch (Exception e) {
			throw new DataNotAddedException("Data is not added");
		}
	}
}
