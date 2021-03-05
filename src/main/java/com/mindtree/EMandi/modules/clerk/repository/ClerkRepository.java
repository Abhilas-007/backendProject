package com.mindtree.EMandi.modules.clerk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.EMandi.modules.clerk.entity.Clerk;

public interface ClerkRepository extends JpaRepository<Clerk, String> {

}
