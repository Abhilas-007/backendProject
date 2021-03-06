package com.mindtree.EMandi.modules.admin.service;

import java.util.List;
import java.util.Map;

import com.mindtree.EMandi.exception.ServiceException;
import com.mindtree.EMandi.modules.admin.entity.Admin;

public interface AdminService {
	public String validateLogin(Map<String, String> map);

	public String addAdmin(Admin admin) throws ServiceException;

	public List<Admin> getAllAdmins() throws ServiceException;
}
