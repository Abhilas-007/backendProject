package com.mindtree.EMandi.modules.buyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.EMandi.modules.buyer.entity.BuyerRequest;
@Repository
public interface BuyerRequestRepository extends JpaRepository<BuyerRequest,Integer>{

}
