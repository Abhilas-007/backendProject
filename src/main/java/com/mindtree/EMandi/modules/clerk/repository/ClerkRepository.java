package com.mindtree.EMandi.modules.clerk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.EMandi.modules.clerk.entity.Clerk;
import com.mindtree.EMandi.modules.mandi.entity.Mandi;

@Repository
public interface ClerkRepository extends JpaRepository<Clerk, String> {
    
	
}
