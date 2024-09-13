package com.codegnan.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codegnan.entity.CustomerRequestEntity;
import com.codegnan.exection.invalidRequestIdExection;
import com.codegnan.service.CustomerRequestServices;

@RestController
@RequestMapping("/admin/request")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerRequestController {
	
	CustomerRequestServices customerRequestServices;

	public CustomerRequestController(CustomerRequestServices customerRequestServices) {
		super();
		this.customerRequestServices = customerRequestServices;
	}
	
	@GetMapping
	public ResponseEntity<List<CustomerRequestEntity>> findAllRequests(){
		List<CustomerRequestEntity> customerRequestEntities = customerRequestServices.findAllRequest();
		ResponseEntity<List<CustomerRequestEntity>> responseEntity = new ResponseEntity<List<CustomerRequestEntity>>(customerRequestEntities,HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerRequestEntity> findRequestsById(@PathVariable Long id) throws invalidRequestIdExection {
		CustomerRequestEntity entity = customerRequestServices.findRequestById(id);
		ResponseEntity<CustomerRequestEntity> responseEntity = new ResponseEntity<>(entity, HttpStatus.FOUND);
		return responseEntity;
	}
	
	@PostMapping
	public ResponseEntity<CustomerRequestEntity> saveRequests(@RequestBody CustomerRequestEntity customerRequestEntity) {
		CustomerRequestEntity entity = customerRequestServices.saveRequest(customerRequestEntity);
		ResponseEntity<CustomerRequestEntity> responseEntity = new ResponseEntity<>(entity, HttpStatus.ACCEPTED);
		return responseEntity;
		
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomerRequestEntity> editRequests(@PathVariable("id") Long id ,@RequestBody CustomerRequestEntity entity) throws  invalidRequestIdExection {
		CustomerRequestEntity requestsaved = customerRequestServices.editRequest(entity);
		ResponseEntity<CustomerRequestEntity> responseEntity = new ResponseEntity<>(requestsaved, HttpStatus.ACCEPTED);
		return responseEntity;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomerRequestEntity>  deleteRequest(@PathVariable("id") Long id) throws invalidRequestIdExection {
		CustomerRequestEntity request = customerRequestServices.deleteRequest(id);
		ResponseEntity<CustomerRequestEntity> responseEntity = new ResponseEntity<>(request,HttpStatus.ACCEPTED);
		return responseEntity;
	}
	
	
	
	

}
