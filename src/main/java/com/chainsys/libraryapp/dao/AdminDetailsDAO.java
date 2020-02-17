package com.chainsys.libraryapp.dao;

import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.chainsys.libraryapp.LibaryModel.AdminDetails;
import com.chainsys.libraryapp.exception.DbException;

public interface AdminDetailsDAO {
	
	@SqlUpdate("insert into admin(admin_name,admin_dob,admin_mail_id,admin_mob_no,admin_password,admin_gender,admin_id) values (?,?,?,?,?,?,admin_id_seq.nextval)")
	public void addNewAdmin(AdminDetails admindetails)throws DbException;
	
	@SqlQuery("select 1 from admin where admin_mail_id=? and admin_password=?")
	public Boolean userLogin(String mailId,String password)throws DbException;

}
