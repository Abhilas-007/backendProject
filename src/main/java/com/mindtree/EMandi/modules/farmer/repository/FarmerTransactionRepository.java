package com.mindtree.EMandi.modules.farmer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.mindtree.EMandi.modules.farmer.entity.FarmerTransaction;
@Repository
public interface FarmerTransactionRepository extends JpaRepository<FarmerTransaction, Integer>
{
	@Query("select c from FarmerTransaction c where c.mandi.mandiPincode=?1")
	List<FarmerTransaction> findByMandiPincode(int mandiCode);
}
