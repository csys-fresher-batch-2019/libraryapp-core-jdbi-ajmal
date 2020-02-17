package com.chainsys.libraryapp.service;

import org.jdbi.v3.core.Jdbi;

import com.chainsys.libraryapp.LibaryModel.AdminDetails;
import com.chainsys.libraryapp.Util.Connectionutil;
import com.chainsys.libraryapp.dao.AdminDetailsDAO;

public class AdminDetailsService {
	
	private Jdbi jdbi = Connectionutil.getJdbi();
	private AdminDetailsDAO adminDetailsDAO= jdbi.onDemand(AdminDetailsDAO.class);
	
	public void addNewAdmin(AdminDetails admindetails)throws Exception
	{
		adminDetailsDAO.addNewAdmin(admindetails);
	}
	public Boolean userLogin(String mailId,String password)throws Exception{
		Boolean valid = adminDetailsDAO.userLogin(mailId, password);
		if(valid == null) {
			throw new Exception("Invalid Email/Password");
		}
		return valid;
	}
}
