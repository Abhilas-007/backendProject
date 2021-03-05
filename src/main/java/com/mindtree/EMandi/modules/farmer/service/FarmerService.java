package com.mindtree.EMandi.modules.farmer.service;

import java.util.Map;

import com.mindtree.EMandi.exception.FarmerException;
import com.mindtree.EMandi.modules.farmer.entity.Farmer;

public interface FarmerService {

	public String validateLogin( Map<String, String> map);
	Farmer createFarmer(Farmer farmer) throws FarmerException;
}
