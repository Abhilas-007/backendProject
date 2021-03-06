package com.mindtree.EMandi.modules.superadmin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.EMandi.modules.superadmin.entity.SuperAdmin;
import com.mindtree.EMandi.modules.superadmin.repository.SuperAdminRepository;
import com.mindtree.EMandi.modules.superadmin.service.SuperAdminService;

@Service
public class SuperAdminServiceImpl implements SuperAdminService{

	@Autowired
	SuperAdminRepository sAdminRepo;

	@Override
	public String validateLogin(Map<String, String> map) {
		SuperAdmin sAdmin = sAdminRepo.findById(map.get("userId")).orElse(null);
		if (sAdmin != null)
			if (sAdmin.getPassword().equals(map.get("password")))
				return map.get("userId");
			else
				return null;
		return null;
	}
}
