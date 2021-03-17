package com.mindtree.EMandi.modules.farmer.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.EMandi.exception.DataNotAddedException;
import com.mindtree.EMandi.exception.FarmerException;
import com.mindtree.EMandi.exception.service.FarmersServiceException;
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
          List<Farmer> farmerList= farmerRepo.findAll();
		 Predicate<Farmer> farmerPredicatec = f-> f.getAadharNumber().equals(farmer.getAadharNumber());
			try {
				for (Farmer farmer2 : farmerList) {
					System.out.println(farmer2.toString());
					if(farmerPredicatec.test(farmer2)) {
						return null;
					}
				}
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
		if(farmer.getSecurityQuestion().equalsIgnoreCase(map.get("sQ"))) {
			if(farmer.getAnswer().equalsIgnoreCase(map.get("answer"))) {
				return "yes";
			}
		}
		return null;
	}

	@Override
	public String updateFarmerProfile(Farmer farmerDetails, Farmer farmer) throws FarmersServiceException {
		System.out.println(farmer.getFarmerName());
		int count =0;
		if(!farmerDetails.getAccountNumber().equals(farmer.getAccountNumber())) {
			farmerDetails.setAccountNumber(farmer.getAccountNumber());
		}
		else {
			count++;
		}
		if(!farmerDetails.getAnswer().equals(farmer.getAnswer())) {
			farmerDetails.setAnswer(farmer.getAnswer());
		}else {
			count++;
		}
		if(!farmerDetails.getBankName().equals(farmer.getBankName())) {
			farmerDetails.setBankName(farmer.getBankName());
		}else {
			count++;
		}
		if(!farmerDetails.getIfsc().equals(farmer.getIfsc())) {
			farmerDetails.setIfsc(farmer.getIfsc());
		}else {
			count++;
		}
		if(!farmerDetails.getFarmerName().equals(farmer.getFarmerName())) {
			farmerDetails.setFarmerName(farmer.getFarmerName());
		}else {
			count++;
		}
		if(!farmerDetails.getMobileNumber().equals(farmer.getMobileNumber())) {
			farmerDetails.setMobileNumber(farmer.getMobileNumber());
		}else {
			count++;
		}
		if(!farmerDetails.getPassword().equals(farmer.getPassword())) {
			farmerDetails.setPassword(farmer.getPassword());
		}else {
			count++;
		}
		if(count==7){
			return null;
		}else {
			farmerRepo.save(farmerDetails);
		}
		return "Updated SuccessFully";
	}
	
	
}
