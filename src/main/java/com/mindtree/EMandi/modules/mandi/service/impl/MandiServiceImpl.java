package com.mindtree.EMandi.modules.mandi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.EMandi.modules.mandi.entity.Mandi;
import com.mindtree.EMandi.modules.mandi.repository.MandiRepository;
import com.mindtree.EMandi.modules.mandi.service.MandiService;

@Service
public class MandiServiceImpl implements MandiService{
	
	@Autowired
	private MandiRepository repository;

	@Override
	public Mandi addMandi(Mandi mandi) {
		// TODO Auto-generated method stub
		repository.save(mandi);
		return mandi;
	}

}
