package com.chainsys.libraryapp.service;

import java.util.ArrayList;

import org.jdbi.v3.core.Jdbi;

import com.chainsys.libraryapp.LibaryModel.StudentDetails;
import com.chainsys.libraryapp.Util.Connectionutil;
import com.chainsys.libraryapp.dao.StudentDetailsDAO;

public class StudentDetailsService {
	
	//private StudentDetailsDAO studentDetailsDAO=new StudentDetailsDAOImp();
	private Jdbi jdbi = Connectionutil.getJdbi();
	private StudentDetailsDAO studentDetailsDAO= jdbi.onDemand(StudentDetailsDAO.class);
	
	public void addStudentDetails(StudentDetails studentdetails)throws Exception
	{
		studentDetailsDAO.addStudentDetails(studentdetails);
	}
	public StudentDetails displayStudentDetail(int studentId)throws Exception{
		return studentDetailsDAO.displayStudentDetail(studentId);
		}
	public ArrayList<StudentDetails> displayAllStudents()throws Exception{
		return studentDetailsDAO.displayAllStudents();
	}

}