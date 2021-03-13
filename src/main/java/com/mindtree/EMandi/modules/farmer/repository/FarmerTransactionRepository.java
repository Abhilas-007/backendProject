package com.mindtree.EMandi.modules.farmer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.EMandi.modules.farmer.entity.FarmerTransaction;

public interface FarmerTransactionRepository extends JpaRepository<FarmerTransaction, Integer>
{

}
