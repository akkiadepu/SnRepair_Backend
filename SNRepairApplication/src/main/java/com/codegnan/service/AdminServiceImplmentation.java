package com.codegnan.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import com.codegnan.config.JwtProvider;
import com.codegnan.entity.EntityAdmin;
import com.codegnan.exection.AdminExecption;
import com.codegnan.reposity.AdminRepo;

@Service
public class AdminServiceImplmentation implements AdminService {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImplmentation.class);

	private AdminRepo adminRepo;
	private JwtProvider jwtProvider;
	
	
	public AdminServiceImplmentation(AdminRepo adminRepo, JwtProvider jwtProvider) {
		super();
		this.adminRepo = adminRepo;
		this.jwtProvider = jwtProvider;
	}

	@Override
	public EntityAdmin findAdminByID(Long adminId) throws AdminExecption {
		logger.debug("finding Admin by id "+adminId);
		Optional<EntityAdmin> user =adminRepo.findById(adminId);
		if(user.isPresent()) {
			logger.debug("returning user "+user.get());
			return user.get();
			
		}
		
		throw new AdminExecption("admin not found with id :"+adminId);
		
		
	}

	@Override
	public EntityAdmin findAdminProfileByJwt(String jwt) throws AdminExecption {
		logger.debug("find Admin Profile by Jwt "+jwt);
		String email =jwtProvider.getEmailFromToken(jwt);
		
		EntityAdmin user =adminRepo.findByEmail(email);
		logger.debug("finding user by email ");
		if(user==null) {
			throw new AdminExecption("admin not found with email :"+email);
		}
		return user;
	}

}
