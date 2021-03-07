package com.mindtree.EMandi.modules.mandi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.EMandi.modules.mandi.entity.Mandi;
@Repository
public interface MandiRepository extends JpaRepository<Mandi,Integer>{

	@Query("select c.clerk.clerkId from Mandi c where c.admin.adminId=?1")
	List<String> findByAdminId(String adminId);
}
