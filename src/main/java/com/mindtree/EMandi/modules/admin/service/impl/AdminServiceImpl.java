package com.mindtree.EMandi.modules.admin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.EMandi.modules.admin.entity.Admin;
import com.mindtree.EMandi.modules.admin.repository.AdminRepository;
import com.mindtree.EMandi.modules.admin.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminRepository adminRepo;

	@Override
	public String validateLogin(Map<String, String> map) {
		Admin admin=adminRepo.findById(map.get("userId")).orElse(null);
		if(admin!=null)
		if(admin.getPassword().equals(map.get("password" )))
			return map.get("userId");
		else return null;
		return null;
	}
}
