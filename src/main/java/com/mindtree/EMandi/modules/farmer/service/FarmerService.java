package com.mindtree.EMandi.modules.farmer.service;

import java.util.List;
import java.util.Map;

import com.mindtree.EMandi.exception.FarmerException;
import com.mindtree.EMandi.exception.ServiceException;
import com.mindtree.EMandi.exception.service.FarmersServiceException;
import com.mindtree.EMandi.modules.clerk.entity.Clerk;
import com.mindtree.EMandi.modules.farmer.entity.Farmer;
import com.mindtree.EMandi.modules.farmer.entity.FarmerTransaction;

public interface FarmerService {

	public String validateLogin(Map<String, String> map);

	Farmer createFarmer(Farmer farmer) throws FarmerException;

	public Farmer getFarmer(int id) throws FarmerException;

	public Farmer updatePassword(Map<String, String> map) throws FarmerException;
	
	public String validateQA(Map<String, String> map) throws FarmerException;

	public String updateFarmerProfile(Farmer farmerDetails, Farmer farmer) throws FarmersServiceException;
	
	public List<FarmerTransaction> findByMandiPincode(int mandiPincode) throws ServiceException;
}
