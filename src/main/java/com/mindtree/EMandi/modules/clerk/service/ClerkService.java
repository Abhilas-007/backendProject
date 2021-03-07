package com.mindtree.EMandi.modules.clerk.service;

import java.util.Map;

import com.mindtree.EMandi.exception.ServiceException;
import com.mindtree.EMandi.modules.admin.entity.Admin;
import com.mindtree.EMandi.modules.clerk.entity.Clerk;

public interface ClerkService {

	public String validateLogin(Map<String, String> map);

	public Clerk getClerk(String id) throws ServiceException;

	public Clerk updatePassword(Map<String, String> map) throws ServiceException;
}
