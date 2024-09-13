package com.codegnan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "Customer-requests")
public class CustomerRequestEntity {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String name;
	private String email;
	private String number;
	private String vehicleModel;
	private String address;
	private String yourMessage;
	
	public CustomerRequestEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerRequestEntity(Long id, String name, String email, String number, String vehicleModel, String address,
			String yourMessage) {
		super();
		Id = id;
		this.name = name;
		this.email = email;
		this.number = number;
		this.vehicleModel = vehicleModel;
		this.address = address;
		this.yourMessage = yourMessage;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getYourMessage() {
		return yourMessage;
	}

	public void setYourMessage(String yourMessage) {
		this.yourMessage = yourMessage;
	}

	
	@Override
	public String toString() {
		return "CustomerRequestEntity [Id=" + Id + ", name=" + name + ", email=" + email + ", number=" + number
				+ ", vehicleModel=" + vehicleModel + ", address=" + address + ", yourMessage=" + yourMessage + "]";
	}
	
	
	
	
	

}
