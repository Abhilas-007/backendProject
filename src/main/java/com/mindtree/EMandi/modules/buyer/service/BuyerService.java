package com.mindtree.EMandi.modules.buyer.service;


import com.mindtree.EMandi.modules.buyer.entity.Buyer;

public interface BuyerService {
	
	void updateBuyer(Buyer buyer);
	
	Buyer getBuyer(int id);
	
	Buyer saveBuyer(Buyer buyer);
}
