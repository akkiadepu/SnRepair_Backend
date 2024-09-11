package com.codegnan.service;


import com.codegnan.entity.EntityAdmin;
import com.codegnan.exection.AdminExecption;

public interface AdminService {
	public EntityAdmin findAdminByID(Long adminId)throws AdminExecption;
	
	public EntityAdmin findAdminProfileByJwt(String jwt)throws AdminExecption ;

}
