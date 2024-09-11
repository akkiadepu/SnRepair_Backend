package com.codegnan.service;

import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.codegnan.entity.EntityAdmin;
import com.codegnan.reposity.AdminRepo;

@Service
public class CustomerAdminServiceImple implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerAdminServiceImple.class);
	
	private AdminRepo adminRepo;
	

	public CustomerAdminServiceImple(AdminRepo adminRepo) {
		
		this.adminRepo = adminRepo;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("load user by username "+username);
		EntityAdmin entityAdmin = adminRepo.findByEmail(username);
		if(entityAdmin==null) {
			throw new UsernameNotFoundException("user not found with email"+username);
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		logger.debug("return user email"+entityAdmin.getEmail()+"user password "+entityAdmin.getPassword());
		return new org.springframework.security.core.userdetails.User(entityAdmin.getEmail(),entityAdmin.getPassword(),authorities);
	}

}
