package com.mindtree.EMandi.modules.clerk.dto;

public class ClerkCropDto {
	private String clerkId;
	private int farmerId;
	private String cropName;
	private double cropQty;

	public String getClerkId() {
		return clerkId;
	}

	public void setClerkId(String clerkId) {
		this.clerkId = clerkId;
	}

	public int getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(int farmerId) {
		this.farmerId = farmerId;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public double getCropQty() {
		return cropQty;
	}

	public void setCropQty(double cropQty) {
		this.cropQty = cropQty;
	}
}
