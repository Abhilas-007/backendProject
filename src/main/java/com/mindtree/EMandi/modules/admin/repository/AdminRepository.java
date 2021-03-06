package com.mindtree.EMandi.modules.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.EMandi.modules.admin.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {

}
