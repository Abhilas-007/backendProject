package com.mindtree.EMandi.modules.buyer.service.impl;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.EMandi.exception.ServiceException;
import com.mindtree.EMandi.modules.buyer.entity.Buyer;
import com.mindtree.EMandi.modules.buyer.repository.BuyerRepository;
import com.mindtree.EMandi.modules.buyer.service.BuyerService;
import com.mindtree.EMandi.modules.farmer.entity.Farmer;

@Service
public class BuyerServiceImpl implements BuyerService{
	
	@Autowired
	private BuyerRepository buyerRepository;

	@Override
	public void updateBuyer(Buyer buyer) {
		// TODO Auto-generated method stub
		buyer.setAnswer(buyerRepository.findById(buyer.getBuyerId()).get().getAnswer());
		buyer.setSecurityQuestion(buyerRepository.findById(buyer.getBuyerId()).get().getSecurityQuestion());
		buyerRepository.save(buyer);
	}

	@Override
	public Buyer getBuyer(int id) throws IllegalArgumentException, NoSuchElementException{
		Buyer buyer;
		try {
			 buyer=buyerRepository.findById(id).get();
		}catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Id is null");
		}catch(NoSuchElementException a) {
			throw new NoSuchElementException("No such element Present");
		}
		return buyer;
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

	@Override
	public String validateQA(Map<String, String> map) {
		Buyer buyer=buyerRepository.findById(Integer.parseInt(map.get("userId"))).get();
		if(buyer.getSecurityQuestion().equalsIgnoreCase(map.get("sQ"))) {
			if(buyer.getAnswer().equalsIgnoreCase(map.get("answer"))) {
				System.out.println(buyer.getAnswer()+"&"+ map.get("answer"));
				return "yes";
			}
		}
		return null;
	}

}
