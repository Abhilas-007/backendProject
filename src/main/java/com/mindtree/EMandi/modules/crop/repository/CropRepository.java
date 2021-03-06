package com.mindtree.EMandi.modules.crop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mindtree.EMandi.modules.crop.entity.Crop;

public interface CropRepository extends JpaRepository<Crop, Integer> 
{
	@Query("select c from Crop c where c.cropName=?1 and c.admin.adminId=?2")
	Crop findMSP(String cropName,String adminId);
	
}
