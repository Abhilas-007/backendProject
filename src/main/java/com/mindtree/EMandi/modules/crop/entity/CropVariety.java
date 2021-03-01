package com.mindtree.EMandi.modules.crop.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(Crop.class)
public class CropVariety {
	
	@Id
	private String cropClass;
	@Id
	private int cropId;
	private double cropQualityPrice;
	private double buyerCropPrice;
	@ManyToOne
	@JoinColumn(name = "cropId")
	private Crop crop;
	
	public CropVariety() {
	}
	
	public CropVariety(String cropClass, int cropId, double cropQualityPrice, double buyerCropPrice) {
		super();
		this.cropClass = cropClass;
		this.cropId = cropId;
		this.cropQualityPrice = cropQualityPrice;
		this.buyerCropPrice = buyerCropPrice;
	}

	public String getCropClass() {
		return cropClass;
	}

	public void setCropClass(String cropClass) {
		this.cropClass = cropClass;
	}

	public int getCropId() {
		return cropId;
	}

	public void setCropId(int cropId) {
		this.cropId = cropId;
	}

	public double getCropQualityPrice() {
		return cropQualityPrice;
	}

	public void setCropQualityPrice(double cropQualityPrice) {
		this.cropQualityPrice = cropQualityPrice;
	}

	public double getBuyerCropPrice() {
		return buyerCropPrice;
	}

	public void setBuyerCropPrice(double buyerCropPrice) {
		this.buyerCropPrice = buyerCropPrice;
	}
	
}
