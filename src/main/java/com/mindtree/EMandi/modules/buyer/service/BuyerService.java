package com.mindtree.EMandi.modules.buyer.service;


import java.util.Map;

import com.mindtree.EMandi.modules.buyer.entity.Buyer;

public interface BuyerService {
	
	void updateBuyer(Buyer buyer);
	
	Buyer getBuyer(int id);
	
	Buyer saveBuyer(Buyer buyer);
	
	public String validateLogin( Map<String, String> map);
	
	public Buyer updatePassword(Map<String, String> map);
	
	public String validateQA(Map<String, String> map);
}
