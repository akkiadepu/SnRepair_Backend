package com.codegnan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codegnan.entity.AdminLogin;

@Repository
public interface AdminRepo extends JpaRepository<AdminLogin, Integer> {
	AdminLogin findByEmail(String email);
	
	

}
