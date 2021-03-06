package com.mindtree.EMandi.modules.crop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.EMandi.modules.crop.entity.Crop;

public interface CropRepository extends JpaRepository<Crop, Integer> 
{

}
