package com.mindtree.EMandi.modules.clerk.service;

import java.util.List;
import java.util.Map;

import com.mindtree.EMandi.exception.ServiceException;
import com.mindtree.EMandi.modules.clerk.dto.ClerkCropDto;
import com.mindtree.EMandi.modules.clerk.entity.Clerk;

public interface ClerkService {

	public String validateLogin(Map<String, String> map);

	public Clerk getClerk(String id) throws ServiceException;

	public Clerk updatePassword(Map<String, String> map) throws ServiceException;
	
	public double getTotalPrice(ClerkCropDto clerkCropDto[]);
	
	public double buyCrops(ClerkCropDto clerkCropDto[]);
	
	public double getStorageByClerkId(String clerkId);
	
	public double getSingleCropPrice(ClerkCropDto clerkCropDto);
	
	public boolean validateFarmerId(int farmerId);
	
	public String passwordMail(Map<String, String> map) throws ServiceException;
	
	public List<Integer> getFarmerIds(String clerkId);
}
