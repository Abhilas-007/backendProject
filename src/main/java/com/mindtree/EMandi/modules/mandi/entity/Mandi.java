package com.mindtree.EMandi.modules.mandi.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.mindtree.EMandi.modules.admin.entity.Admin;
import com.mindtree.EMandi.modules.buyer.entity.BuyerTransaction;
import com.mindtree.EMandi.modules.clerk.entity.Clerk;
import com.mindtree.EMandi.modules.farmer.entity.FarmerTransaction;

@Entity
public class Mandi {
	
	@Id
	private int mandiPincode;
	private double storage;
	@OneToMany(mappedBy = "mandi")
	private Set<FarmerTransaction> farmerTransactions;
	@OneToOne
	@JoinColumn(name = "clerkId")
	private Clerk clerk;
	@OneToMany(mappedBy = "mandi")
	private Set<BuyerTransaction> buyerTransactions;
	@ManyToOne
	@JoinColumn(name = "adminId")
	private Admin admin;
	
	public Mandi() {
	}
	
	public Mandi(int mandiPincode, double storage) {
		super();
		this.mandiPincode = mandiPincode;
		this.storage = storage;
	}

	public int getMandiPincode() {
		return mandiPincode;
	}
	
	public void setMandiPincode(int mandiPincode) {
		this.mandiPincode = mandiPincode;
	}
	
	public double getStorage() {
		return storage;
	}
	
	public void setStorage(double storage) {
		this.storage = storage;
	}
	
	
}
