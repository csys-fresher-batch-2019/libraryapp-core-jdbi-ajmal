package com.chainsys.libraryapp.dao;

import java.sql.Date;
import java.util.ArrayList;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.chainsys.libraryapp.dao.mapper.StudentDetailsRowMapper;
import com.chainsys.libraryapp.exception.DbException;
import com.chainsys.libraryapp.model.StudentDetails;


public interface StudentDetailsDAO {
	
	@SqlUpdate("insert into student(std_name,std_dept,std_dob,std_mail_id,std_mob_no,joining_yr,std_id) values(?,?,?,?,?,?,std_seq.nextval)")
	public void addStudentDetails(StudentDetails studentdetails)throws DbException;
	
	@SqlQuery("select * from student where std_id=?")
	 @RegisterRowMapper(StudentDetailsRowMapper.class)
	public StudentDetails displayStudentDetail(int studentId)throws DbException;
	
	@SqlQuery("select * from student")
	 @RegisterRowMapper(StudentDetailsRowMapper.class)
	public ArrayList<StudentDetails> displayAllStudents()throws DbException;
	
	@SqlQuery("select 1 form student where std_id=? and std_dob=?")	 
	public Boolean studentLogin(int studentId,Date studentDOB)throws DbException;

	@SqlQuery("select 1 from student where std_id=?")
	public Integer findByStudentId(int studentId);
	
}
