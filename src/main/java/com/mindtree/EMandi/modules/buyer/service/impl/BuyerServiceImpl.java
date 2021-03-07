package com.mindtree.EMandi.modules.buyer.service.impl;

import java.util.Map;

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

	@Override
	public Buyer saveBuyer(Buyer buyer) {
		// TODO Auto-generated method stub
		
		return buyerRepository.save(buyer);
	}
	@Override
	public String validateLogin(Map<String, String> map) {
		Buyer buyer = buyerRepository.findById((Integer.parseInt(map.get("userId")))).orElse(null);
		if (buyer != null)
			if (buyer.getPassword().equals(map.get("password")))
				return map.get("userId");
			else
				return null;
		return null;
	}

	@Override
	public Buyer updatePassword(Map<String, String> map) {
		int id=Integer.parseInt(map.get("userId"));
		Buyer buyer=buyerRepository.findById(id).get();
		buyer.setPassword(map.get("password"));
		buyerRepository.save(buyer);
		return buyer;
	}

}
