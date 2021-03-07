package com.mindtree.EMandi.modules.superadmin.service;

import java.util.Map;

import com.mindtree.EMandi.exception.ServiceException;
import com.mindtree.EMandi.modules.buyer.entity.Buyer;
import com.mindtree.EMandi.modules.superadmin.entity.SuperAdmin;

public interface SuperAdminService {

	public String validateLogin(Map<String, String> map);

	public SuperAdmin getSAdmin(int id) throws ServiceException;

	public SuperAdmin updatePassword(Map<String, String> map);
}
