package com.mindtree.EMandi.modules.clerk.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.EMandi.exception.ServiceException;
import com.mindtree.EMandi.modules.admin.entity.Admin;
import com.mindtree.EMandi.modules.clerk.entity.Clerk;
import com.mindtree.EMandi.modules.clerk.repository.ClerkRepository;
import com.mindtree.EMandi.modules.clerk.service.ClerkService;

@Service
public class ClerkServiceImpl implements ClerkService {

	@Autowired
	ClerkRepository clerkRepo;

	@Override
	public String validateLogin(Map<String, String> map) {
		Clerk clerk = clerkRepo.findById(map.get("userId")).orElse(null);
		if (clerk != null)
			if (clerk.getPassword().equals(map.get("password")))
				return map.get("userId");
			else
				return null;
		return null;
	}

	@Override
	public Clerk getClerk(String id) throws ServiceException {
		Clerk clerk;
		try {
			clerk = clerkRepo.findById(id).get();
		} catch (IllegalArgumentException e) {
			throw new ServiceException("No data found for that id", e);
		}

		return clerk;
	}

	@Override
	public Clerk updatePassword(Map<String, String> map) throws ServiceException {
		String id = map.get("userId");
		Clerk clerk;
		try {
			clerk = clerkRepo.findById(id).get();
			clerk.setPassword(map.get("password"));
			clerkRepo.save(clerk);
		} catch (IllegalArgumentException e) {
			throw new ServiceException("Password couldnt be updated");
		}
		return clerk;
	}
}
