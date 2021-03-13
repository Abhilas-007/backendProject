package com.mindtree.EMandi.modules.mandi.service;

import com.mindtree.EMandi.modules.mandi.entity.Mandi;

public interface MandiService {
	
	Mandi addMandi(Mandi mandi);
	
	Mandi getMandiByMandiPincode(int mandiPincode);
	
	Mandi updateMandiStorage(int mandiPincode, double storage);
}
