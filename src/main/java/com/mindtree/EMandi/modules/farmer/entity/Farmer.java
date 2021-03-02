package com.mindtree.EMandi.modules.farmer.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Farmer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int farmerId;
	private String farmerName,bankName,accountNumber,ifsc,aadharNumber,password,mobileNumber;
	@OneToMany(mappedBy = "farmer")
	private Set<FarmerTransaction> transactions; 
	
	public Farmer() {
	}
	
	public Farmer(int farmerId, String farmerName, String bankName, String accountNumber, String ifsc,
			String aadharNumber, String password, String mobileNumber) {
		super();
		this.farmerId = farmerId;
		this.farmerName = farmerName;
		this.bankName = bankName;
		this.accountNumber = accountNumber;
		this.ifsc = ifsc;
		this.aadharNumber = aadharNumber;
		this.password = password;
		this.mobileNumber = mobileNumber;
	}

	public int getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(int farmerId) {
		this.farmerId = farmerId;
	}

	public String getFarmerName() {
		return farmerName;
	}

	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
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

	public Set<FarmerTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<FarmerTransaction> transactions) {
		this.transactions = transactions;
	}
	
	
	
	
}
