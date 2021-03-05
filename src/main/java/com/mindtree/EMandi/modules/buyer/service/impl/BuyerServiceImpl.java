package com.mindtree.EMandi.modules.buyer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.EMandi.modules.buyer.entity.Buyer;
import com.mindtree.EMandi.modules.buyer.repository.BuyerRepository;
import com.mindtree.EMandi.modules.buyer.service.BuyerService;

@Service
public class BuyerServiceImpl implements BuyerService{
	
	@Autowired
	private BuyerRepository buyerRepository;

	@Override
	public void updateBuyer(Buyer buyer) {
		// TODO Auto-generated method stub
		buyerRepository.save(buyer);
	}

	@Override
	public Buyer getBuyer(int id) {
		// TODO Auto-generated method stub
		return buyerRepository.findById(id).get();
	}

}
