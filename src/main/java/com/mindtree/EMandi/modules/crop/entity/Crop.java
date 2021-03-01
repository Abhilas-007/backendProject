package com.mindtree.EMandi.modules.crop.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.mindtree.EMandi.modules.admin.entity.Admin;

@Entity
public class Crop implements Serializable{
	
	@Id
	private int cropId;
	private String cropName;
	private double cropMSP;
	@ManyToOne
	@JoinColumn(name = "adminId")
	private Admin admin;
	@OneToMany(mappedBy = "crop")
	private List<CropVariety> varieties;
	
	public Crop() {
	}
	
	public Crop(int cropId, String cropName, double cropMSP) {
		super();
		this.cropId = cropId;
		this.cropName = cropName;
		this.cropMSP = cropMSP;
	}

	public int getCropId() {
		return cropId;
	}

	public void setCropId(int cropId) {
		this.cropId = cropId;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public double getCropMSP() {
		return cropMSP;
	}

	public void setCropMSP(double cropMSP) {
		this.cropMSP = cropMSP;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cropId;
		result = prime * result + ((varieties == null) ? 0 : varieties.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Crop other = (Crop) obj;
		if (cropId != other.cropId)
			return false;
		if (varieties == null) {
			if (other.varieties != null)
				return false;
		} else if (!varieties.equals(other.varieties))
			return false;
		return true;
	}
	
}
