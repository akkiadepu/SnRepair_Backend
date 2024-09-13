package com.codegnan.reposity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codegnan.entity.CustomerRequestEntity;

@Repository
public interface CustomerRequestRepo extends JpaRepository<CustomerRequestEntity, Long>  {
	
	

}
