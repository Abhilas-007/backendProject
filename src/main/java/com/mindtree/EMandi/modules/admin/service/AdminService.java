package com.mindtree.EMandi.modules.admin.service;

import java.util.List;
import java.util.Map;

import com.mindtree.EMandi.exception.ServiceException;
import com.mindtree.EMandi.modules.admin.entity.Admin;
import com.mindtree.EMandi.modules.buyer.entity.Buyer;
import com.mindtree.EMandi.modules.farmer.entity.Farmer;

public interface AdminService {
	public String validateLogin(Map<String, String> map);

	public String addAdmin(Admin admin) throws ServiceException;

	public List<Admin> getAllAdmins() throws ServiceException;

	public Admin getAdmin(String id) throws ServiceException;
	
	public Admin updatePassword(Map<String, String> map) throws ServiceException;
	
	public List<Farmer> getFarmersByAdminIdAndMandiPincode(String adminId, int mandiPincode) throws ServiceException;
	
	public List<Buyer> getBuyersByAdminIdAndMandiPincode(String adminId, int mandiPincode) throws ServiceException;
	
	public String passwordMail(Map<String, String> map) throws ServiceException;
	
	public Admin getAdminByState(String state) throws ServiceException;
}
