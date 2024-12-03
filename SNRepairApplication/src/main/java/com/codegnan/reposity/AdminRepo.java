package com.codegnan.reposity;

import org.springframework.data.jpa.repository.JpaRepository;


import com.codegnan.entity.EntityAdmin;

public interface AdminRepo extends JpaRepository<EntityAdmin, Long> {
	
	public EntityAdmin findByEmail(String email);
	

}

