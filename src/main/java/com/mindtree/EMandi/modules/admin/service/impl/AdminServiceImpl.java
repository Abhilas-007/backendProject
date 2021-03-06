package com.mindtree.EMandi.modules.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.EMandi.exception.ResourceNotFoundException;
import com.mindtree.EMandi.exception.ServiceException;
import com.mindtree.EMandi.modules.admin.entity.Admin;
import com.mindtree.EMandi.modules.admin.repository.AdminRepository;
import com.mindtree.EMandi.modules.admin.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepo;

	@Override
	public String validateLogin(Map<String, String> map) {
		Admin admin = adminRepo.findById(map.get("userId")).orElse(null);
		if (admin != null)
			if (admin.getPassword().equals(map.get("password")))
				return map.get("userId");
			else
				return null;
		return null;
	}

	@Override
	public String addAdmin(Admin admin) throws ServiceException {
		try {
			adminRepo.save(admin);
		} catch (Exception e) {
			throw new ServiceException("Some exception occured while adding data to DB.", e);
		}
		return "Successfully added admin";
	}

	@Override
	public List<Admin> getAllAdmins() throws ServiceException {
		List<Admin> admins = null;
		try {
			admins = adminRepo.findAll();
			if (admins.isEmpty()) {
				throw new ResourceNotFoundException();
			}
		} catch (ResourceNotFoundException e) {
			throw new ServiceException("No data found.", e);
		} catch (Exception e) {
			throw new ServiceException("Some exception occured while grabbing data from DB.", e);
		}
		return admins;
	}
}
