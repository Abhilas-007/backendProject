package com.mindtree.EMandi.modules.farmer.dto;

public class FarmerTransactionDto1 {
	
	private int transactionId;
	private String cropName;
	private String cropClass;
	private double quantity;
	private double amount;
	
	public FarmerTransactionDto1() {
	}
	
	public FarmerTransactionDto1(int transactionId, String cropName, String cropClass, double quantity, double amount) {
		super();
		this.transactionId = transactionId;
		this.cropName = cropName;
		this.cropClass = cropClass;
		this.quantity = quantity;
		this.amount = amount;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public String getCropClass() {
		return cropClass;
	}

	public void setCropClass(String cropClass) {
		this.cropClass = cropClass;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
