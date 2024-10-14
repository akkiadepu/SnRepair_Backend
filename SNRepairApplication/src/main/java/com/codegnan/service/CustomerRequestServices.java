package com.codegnan.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codegnan.entity.CustomerRequestEntity;
import com.codegnan.exection.invalidRequestIdExection;
import com.codegnan.reposity.CustomerRequestRepo;

@Service
public class CustomerRequestServices {
	
	@Autowired
	CustomerRequestRepo customerRequestRepo;

	
	public CustomerRequestServices(CustomerRequestRepo customerRequestRepo) {
		super();
		this.customerRequestRepo = customerRequestRepo;
	}
	
	public List<CustomerRequestEntity> findAllRequest() {
		return customerRequestRepo.findAll();
		
	}
	
	public CustomerRequestEntity findRequestById(Long id) throws invalidRequestIdExection {
		Optional<CustomerRequestEntity> optional = customerRequestRepo.findById(id);
		if(!optional.isPresent()) {
			throw new invalidRequestIdExection("Request id "+id+"is not valid");
		}
		return optional.get();
	}
	
	public CustomerRequestEntity saveRequest(CustomerRequestEntity requestEntity) {
		 requestEntity.setSubmittedAt(new Date());
		return customerRequestRepo.save(requestEntity);
	}
	
	public CustomerRequestEntity editRequest(CustomerRequestEntity requestEntity) throws invalidRequestIdExection {
		findRequestById(requestEntity.getId());
		 // Optionally update the submittedAt field if desired
        // requestEntity.setSubmittedAt(new Date());
		customerRequestRepo.save(requestEntity);
		return requestEntity;
		
	}
	
	public CustomerRequestEntity deleteRequest(Long id) throws invalidRequestIdExection {
		CustomerRequestEntity requestEntity= findRequestById(id);
		customerRequestRepo.deleteById(id);
		return requestEntity;
	}

	
	
	
	

}
