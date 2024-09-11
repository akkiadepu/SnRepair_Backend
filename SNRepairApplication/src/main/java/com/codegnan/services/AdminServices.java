package com.codegnan.services;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.codegnan.entity.AdminLogin;
import com.codegnan.exception.InvalidAdminIdException;
import com.codegnan.exception.UserNameNotFound;
import com.codegnan.exception.UsernameNotFoundException;
import com.codegnan.repo.AdminRepo;

@Service
public class AdminServices implements UserDetailsService {
	
	AdminRepo adminRepo;
	
	public AdminServices() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public AdminServices(AdminRepo adminRepo) {
		super();
		this.adminRepo = adminRepo;
	}
	
	 public void CustomUserDetailsService(AdminRepo adminRepo) {
	        this.adminRepo = adminRepo;
	    }
	
	public List<AdminLogin> findAllAdmins(){
		return adminRepo.findAll();
	}
	
//	public AdminLogin findAdminbyID(int id) throws InvalidAdminIdException {
//		Optional<AdminLogin> admin =adminRepo.findById(id);
//		if(!admin.isPresent()) {
//			throw new InvalidAdminIdException("admin ID "+id+" is not valid ");
//		}
//		return admin.get();
//	}
	
	public AdminLogin saveAdmin(AdminLogin adminLogin) {
		return adminRepo.save(adminLogin);
	}
	
	public AdminLogin editAdmin(AdminLogin adminLogin) throws InvalidAdminIdException {
//		findAdminbyID(adminLogin.getId());
		adminRepo.save(adminLogin);
		return adminLogin;
		
	}
//	
//	public AdminLogin deleteAdmin(int id) throws InvalidAdminIdException {
////		AdminLogin adminLogin = findAdminbyID(id);
//		adminRepo.deleteById(id);
//		return adminLogin;
//		
//	}
	
	  public boolean authenticateAdmin(String email, String password) {
//	        logger.info("Authenticating admin with email: {}", email);
//	        AdminLogin admin = adminRepo.findByEmail(email);
//	        System.out.println(email);
//	        System.out.println(password);
//	        if (admin != null) {
////	            logger.info("Found admin: {}", admin);
//	            boolean isAuthenticated = admin.getPassword().equals(password);
////	            logger.info("Authentication result: {}", isAuthenticated);
//	            return isAuthenticated;
//	        } else {
////	            logger.info("Admin not found for email: {}", email);
//	            return false;
//	        }
		  
		  return email.equals("akki.adepu@gmail.com") && password.equals("Akhila@02");
	    }
	  
	  
	  @Override
	    public UserDetails loadUserByUsername(String email) {
	        AdminLogin admin = adminRepo.findByEmail(email);
	        if (admin == null) {
	            System.out.println("Admin not found");
	        }
	        return User.withUsername(admin.getEmail())
	                   .password("{noop}" + admin.getPassword()) // {noop} is used for plaintext password, for demo purposes
	                   .roles("ADMIN")
	                   .build();
	    }


	

}
