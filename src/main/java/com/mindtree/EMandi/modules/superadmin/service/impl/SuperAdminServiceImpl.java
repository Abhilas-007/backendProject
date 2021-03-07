package com.mindtree.EMandi.modules.superadmin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.EMandi.exception.ServiceException;
import com.mindtree.EMandi.modules.admin.entity.Admin;
import com.mindtree.EMandi.modules.buyer.entity.Buyer;
import com.mindtree.EMandi.modules.superadmin.entity.SuperAdmin;
import com.mindtree.EMandi.modules.superadmin.repository.SuperAdminRepository;
import com.mindtree.EMandi.modules.superadmin.service.SuperAdminService;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {

	@Autowired
	SuperAdminRepository sAdminRepo;

	@Override
	public String validateLogin(Map<String, String> map) {
		SuperAdmin sAdmin = sAdminRepo.findById(Integer.parseInt(map.get("userId"))).orElse(null);
		if (sAdmin != null)
			if (sAdmin.getPassword().equals(map.get("password")))
				return map.get("userId");
			else
				return null;
		return null;
	}

	@Override
	public SuperAdmin getSAdmin(int id) throws ServiceException {
		SuperAdmin sAdmin;
		try {
			sAdmin = sAdminRepo.findById(id).get();
		} catch (IllegalArgumentException e) {
			throw new ServiceException("No data found for that id", e);
		}

		return sAdmin;
	}

	@Override
	public SuperAdmin updatePassword(Map<String, String> map) {
		int id = Integer.parseInt(map.get("userId"));
		SuperAdmin sAdmin = sAdminRepo.findById(id).get();
		sAdmin.setPassword(map.get("password"));
		sAdminRepo.save(sAdmin);
		return sAdmin;

	}
}
