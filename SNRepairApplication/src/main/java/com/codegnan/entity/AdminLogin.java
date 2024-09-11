package com.codegnan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="admin")
public class AdminLogin {


	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String email;
	private String password;
	
	
	
	public AdminLogin() {
		super();
		// TODO Auto-generated constructor stub
	}
//	public AdminLogin(int id, String email, String password) {
//		super();
////		this.id = id;
//		this.email = email;
//		this.password = password;
//	}
	public AdminLogin(String email,String password) {
		super();
		this.email=email;
		this.password=password;
	}
	
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AdminLogin [id="+ ", email=" + email + ", password=" + password + "]";
	}
}
