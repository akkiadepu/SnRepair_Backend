package com.codegnan.controller;




import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.codegnan.config.JwtProvider;
import com.codegnan.entity.EntityAdmin;
import com.codegnan.exection.AdminExecption;
import com.codegnan.reposity.AdminRepo;
import com.codegnan.request.LoginRequest;
import com.codegnan.response.AuthResponse;
import com.codegnan.service.CustomerAdminServiceImple;


@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AuthController {
	
	
	private AdminRepo adminRepo;
	private JwtProvider jwtProvider;
	private PasswordEncoder passwordEncoder;
	private CustomerAdminServiceImple adminServiceImple;
	
	
	public AuthController(AdminRepo adminRepo, JwtProvider jwtProvider, PasswordEncoder passwordEncoder,
			CustomerAdminServiceImple adminServiceImple) {
	
		this.adminRepo = adminRepo;
		this.jwtProvider = jwtProvider;
		this.passwordEncoder = passwordEncoder;
		this.adminServiceImple = adminServiceImple;
		
	}
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody EntityAdmin user) throws AdminExecption  {
		String email= user.getEmail();
		String password =user.getPassword();
		String firstname = user.getFirstname();
		String lastname=user.getLastname();
		
		EntityAdmin isEmailExist= adminRepo.findByEmail(email);
		if(isEmailExist!=null) {
			throw new AdminExecption("Email is ALready used with another account ");
		}
		
		EntityAdmin createdUser= new EntityAdmin();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFirstname(firstname);
		createdUser.setLastname(lastname);
		
		EntityAdmin savedUser= adminRepo.save(createdUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
				
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse =new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("signup Successfull");
		
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
		
		
	}
	
	
	
	@PostMapping("/login")
	 public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginResquest) {
		 
		 String username =loginResquest.getEmail();
		 String password= loginResquest.getPassword();
		 Authentication authentication= authenticate(username,password);
		 SecurityContextHolder.getContext().setAuthentication(authentication);
		 
		 String token = jwtProvider.generateToken(authentication);
			
		 AuthResponse authResponse =new AuthResponse();
			authResponse.setJwt(token);
			authResponse.setMessage("login Successfull");
			
			return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
		 
		 
	
		   
	   }

	
	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = adminServiceImple.loadUserByUsername(username);
		
		if(userDetails==null) {
			throw new BadCredentialsException("Invalid username ");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid password ");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}
	
	
//	@PostMapping("/verify-token")
//	public ResponseEntity<?> verifyToken(@RequestBody String token) {
//	    if (token == null || token.isEmpty()) {
//	        return new ResponseEntity<>("Token is missing or malformed", HttpStatus.BAD_REQUEST);
//	    }
//	    
//	    // Remove "Bearer " prefix if present
//	    if (token.startsWith("Bearer ")) {
//	        token = token.substring(7);
//	    }
//	    
//	    if (jwtProvider.validateToken(token)) {
//	        return new ResponseEntity<>("Token is valid", HttpStatus.OK);
//	    } else {
//	        return new ResponseEntity<>("Invalid or expired token", HttpStatus.UNAUTHORIZED);
//	    }
//	}



	
	
	
	
	

}
