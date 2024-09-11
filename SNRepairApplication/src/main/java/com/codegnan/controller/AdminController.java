package com.codegnan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.codegnan.entity.AdminLogin;
import com.codegnan.exception.InvalidAdminIdException;
import com.codegnan.services.AdminServices;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
//@RequestMapping("/admin")
@SessionAttributes("email")
@CrossOrigin(origins = "http://localhost:3000/" ,allowCredentials = "true")

public class AdminController {
	
//	@Autowired
//	private AdminServices adminServices;
//
//	public AdminController(AdminServices adminServices) {
//		super();
//		this.adminServices = adminServices;
//	}
//	
//	@GetMapping
//	public ResponseEntity<List<AdminLogin>> findAllAdmin() {
//		List<AdminLogin> adminLogins =adminServices.findAllAdmins();
//		ResponseEntity<List<AdminLogin>> responseEntity = new ResponseEntity<List<AdminLogin>>(adminLogins, HttpStatus.OK);
//		return responseEntity;
//		
//	}
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<AdminLogin> findAdminById(@PathVariable int id,HttpSession session) throws InvalidAdminIdException {
//		if(session.getAttribute("email")== null) {
//			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//		}
//		AdminLogin adminLogin = adminServices.findAdminbyID(id);
//		ResponseEntity<AdminLogin> responseEntity = new ResponseEntity<>(adminLogin, HttpStatus.FOUND);
//		return responseEntity;
//	}
//	
////	@PostMapping
////	public ResponseEntity<AdminLogin> saveAdmin(@RequestBody AdminLogin admin) {
////		AdminLogin adminLogin = adminServices.saveAdmin(admin);
////		ResponseEntity<AdminLogin> responseEntity = new ResponseEntity<>(adminLogin, HttpStatus.ACCEPTED);
////		return responseEntity;
////		
////	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<AdminLogin> editAdmin(@PathVariable("id") int id ,@RequestBody AdminLogin adminlogin ,HttpSession session) throws InvalidAdminIdException {
//		 if (session.getAttribute("email") == null) {
//	            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//	        }
//		AdminLogin adminLogin2 = adminServices.editAdmin(adminlogin);
//		ResponseEntity<AdminLogin> responseEntity = new ResponseEntity<>(adminLogin2, HttpStatus.ACCEPTED);
//		return responseEntity;
//	}
//	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<AdminLogin>  deleteAdmin(@PathVariable("id") int id,HttpSession session) throws InvalidAdminIdException{
//		  if (session.getAttribute("email") == null) {
//	            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//	        }
//		AdminLogin adminLogin = adminServices.deleteAdmin(id);
//		ResponseEntity<AdminLogin> responseEntity = new ResponseEntity<>(adminLogin, HttpStatus.ACCEPTED);
//		return responseEntity;
//	}
//	
//	
////	@PostMapping("/login")
////	public  ResponseEntity<String> LoginAdmin(@RequestBody AdminLogin adminLogin,HttpSession session) {
////		
//////		@RequestBody AdminLogin adminLogin
//////		AdminLogin adminLogin2 = adminServices.saveAdmin(adminLogin);
//////		 System.out.println("Received login reques	t: " + adminLogin);
////		String email = adminLogin.getEmail();
////	    String password = adminLogin.getPassword();
////		 System.out.println("Received login request: " + email);
////		 System.out.println("Received login request: " + password);
////		boolean isAuthenticated = adminServices.authenticateAdmin(email,password);
////		if(isAuthenticated) {
////			session.setAttribute("email", email);
////			return new ResponseEntity<>("Login successfull",HttpStatus.OK);
////		}
////		else {
////			return new ResponseEntity<>("Invalid Login credentials",HttpStatus.UNAUTHORIZED);
////		}
////		
////	}
//	
//	
//	@PostMapping("/login")
//    public ResponseEntity<String> loginAdmin(@RequestBody AdminLogin adminLogin, HttpSession session) {
//        try {
//            String email = adminLogin.getEmail();
//            String password = adminLogin.getPassword();
//            boolean isAuthenticated = adminServices.authenticateAdmin(email, password);
//            if (isAuthenticated) {
//                session.setAttribute("email", email);
//                return new ResponseEntity<>("Login successful", HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Invalid login credentials", HttpStatus.UNAUTHORIZED);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//	
//	  @PostMapping("/logout")
//	    public ResponseEntity<String> logoutAdmin(HttpSession session) {
//	        session.invalidate();
//	        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
//	    }
//	  
//	  
//	  @GetMapping("/check-session")
//	    public ResponseEntity<String> checkSession(HttpSession session) {
//	        if (session.getAttribute("email") != null) {
//	            return new ResponseEntity<>("Session active", HttpStatus.OK);
//	        } else {
//	            return new ResponseEntity<>("Session inactive", HttpStatus.UNAUTHORIZED);
//	        }
//	    }
	
	
	
	

	

	    @Autowired
	    private AdminServices adminServices;

	    public AdminController(AdminServices adminServices) {
	        super();
	        this.adminServices = adminServices;
	    }
//
//	    @GetMapping
//	    public ResponseEntity<List<AdminLogin>> findAllAdmin() {
//	        List<AdminLogin> adminLogins = adminServices.findAllAdmins();
//	        ResponseEntity<List<AdminLogin>> responseEntity = new ResponseEntity<>(adminLogins, HttpStatus.OK);
//	        return responseEntity;
//	    }

//	    @GetMapping("/{id}")
//	    public ResponseEntity<AdminLogin> findAdminById(@PathVariable int id, HttpSession session) throws InvalidAdminIdException {
//	        if (session.getAttribute("email") == null) {
//	            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//	        }
//	        AdminLogin adminLogin = adminServices.findAdminbyID(id);
//	        ResponseEntity<AdminLogin> responseEntity = new ResponseEntity<>(adminLogin, HttpStatus.FOUND);
//	        return responseEntity;
//	    }

//	    @PutMapping("/{id}")
//	    public ResponseEntity<AdminLogin> editAdmin(@PathVariable("id") int id, @RequestBody AdminLogin adminlogin, HttpSession session) throws InvalidAdminIdException {
//	        if (session.getAttribute("email") == null) {
//	            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//	        }
//	        AdminLogin adminLogin2 = adminServices.editAdmin(adminlogin);
//	        ResponseEntity<AdminLogin> responseEntity = new ResponseEntity<>(adminLogin2, HttpStatus.ACCEPTED);
//	        return responseEntity;
//	    }

//	    @DeleteMapping("/{id}")
//	    public ResponseEntity<AdminLogin> deleteAdmin(@PathVariable("id") int id, HttpSession session) throws InvalidAdminIdException {
//	        if (session.getAttribute("email") == null) {
//	            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//	        }
//	        AdminLogin adminLogin = adminServices.deleteAdmin(id);
//	        ResponseEntity<AdminLogin> responseEntity = new ResponseEntity<>(adminLogin, HttpStatus.ACCEPTED);
//	        return responseEntity;
//	    }

//	    @GetMapping("/login")
//	    public ResponseEntity<String> loginAdmin(@RequestParam String email, @RequestParam String password, HttpSession session) {
//	        System.out.println("Received email: " + email);
//	        System.out.println("Received password: " + password);
//
//	        try {
//	            boolean isAuthenticated = adminServices.authenticateAdmin(email, password);
//	            if (isAuthenticated) {
//	                session.setAttribute("email", email);
//	                return new ResponseEntity<>("Login successful", HttpStatus.OK);
//	            } else {
//	                return new ResponseEntity<>("Invalid login credentials", HttpStatus.UNAUTHORIZED);
//	            }
//	        } catch (Exception e) {
//	            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//	        }
//	    }
	    
//	    @GetMapping("/login")
//	    public ResponseEntity<String> login(@RequestParam String email ,@RequestParam String password, HttpSession session) {
//	        // Assume authentication is successful
//	        session.setAttribute("email", email);
//	        System.out.println("Received email: " + email);
//	        System.out.println("Received password: " + password);
//	        
//	        boolean isAuthenticated = adminServices.authenticateAdmin(email, password);
//            if (isAuthenticated) {
//             session.setAttribute("email", email);
//            	 System.out.println("Received email: " + email);
//                 System.out.println("Received password: " + password);
//                return new ResponseEntity<>("Login successful", HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Invalid login credentials", HttpStatus.UNAUTHORIZED);
//            }
////	        return ResponseEntity.ok().build();
//	    }
	    
//	    @PostMapping(value = "/",meth)?
	    @RequestMapping(value="/", method= RequestMethod.POST)
	    public ResponseEntity<String> loginadd(@RequestBody AdminLogin loginRequest, HttpSession session) {
	        String email = loginRequest.getEmail();
	        
	        String password = loginRequest.getPassword();

	        boolean isAuthenticated = adminServices.authenticateAdmin(email, password);
	        System.out.println(email);
	        System.out.println(password);
	        if (isAuthenticated) {
	            session.setAttribute("email", email);
	            return new ResponseEntity<>("Login successful", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Invalid login credentials", HttpStatus.UNAUTHORIZED);
	        }
	    }


	    @PostMapping("/logout")
	    public ResponseEntity<String> logoutAdmin(HttpSession session) {
	        session.invalidate();
	        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
	    }

//	    @GetMapping("/check-session")
//	    public ResponseEntity<Map<String, Object>> checkSession(HttpSession session) {
//	        Map<String, Object> response = new HashMap<>();
//	        Object email = session.getAttribute("email");
//
//	        if (email != null) {
//	            response.put("isAuthenticated", true);
//	            return ResponseEntity.ok(response);
//	        } else {
//	            response.put("isAuthenticated", false);
//	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//	        }
//	    }
//
//
//	

	
	


}
