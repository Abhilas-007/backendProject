package com.mindtree.EMandi.modules.clerk.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.mindtree.EMandi.modules.mandi.entity.Mandi;

@Entity
public class Clerk {
	
	@Id
	private String clerkId;
	private String clerkName,password,mobileNumber;
	@OneToOne(mappedBy = "clerk")
	private Mandi mandi;
	
	public Clerk() {
	}
	
	public Clerk(String clerkId, String clerkName, String password, String mobileNumber) {
		super();
		this.clerkId = clerkId;
		this.clerkName = clerkName;
		this.password = password;
		this.mobileNumber = mobileNumber;
	}
	
	public String getClerkId() {
		return clerkId;
	}
	public void setClerkId(String clerkId) {
		this.clerkId = clerkId;
	}
	public String getClerkName() {
		return clerkName;
	}
	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	
}
