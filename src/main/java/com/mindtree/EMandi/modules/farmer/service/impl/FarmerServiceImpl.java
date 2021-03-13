package com.mindtree.EMandi.modules.farmer.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.EMandi.exception.DataNotAddedException;
import com.mindtree.EMandi.exception.FarmerException;
import com.mindtree.EMandi.modules.farmer.entity.Farmer;
import com.mindtree.EMandi.modules.farmer.repository.FarmerRepository;
import com.mindtree.EMandi.modules.farmer.service.FarmerService;

@Service
public class FarmerServiceImpl implements FarmerService {

	@Autowired
	FarmerRepository farmerRepo;

	@Override
	public String validateLogin(Map<String, String> map) {
		Farmer farmer = farmerRepo.findById(Integer.parseInt(map.get("userId"))).get();
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

	@Override
	public Farmer getFarmer(int id) throws FarmerException {
		Farmer farmer;
		try {
			farmer = farmerRepo.findById(id).get();
		} catch (IllegalArgumentException e) {
			throw new FarmerException("No data found for that id", e);
		}

		return farmer;
	}

	@Override
	public Farmer updatePassword(Map<String, String> map) throws FarmerException {
		int id = Integer.parseInt(map.get("userId"));
		Farmer farmer;
		try {
			farmer = farmerRepo.findById(id).get();
			farmer.setPassword(map.get("password"));
			farmerRepo.save(farmer);
		} catch (IllegalArgumentException e) {
			throw new FarmerException("Password couldnt be changed");
		}
		return farmer;

	}

	@Override
	public String validateQA(Map<String, String> map) throws FarmerException {
		Farmer farmer=farmerRepo.findById(Integer.parseInt(map.get("userId"))).get();
		if(farmer.getSecurityQuestion()==map.get("sQ")) {
			if(farmer.getAnswer()==map.get("answer")) {
				return "yes";
			}
		}
		return null;
	}
	
	
}
