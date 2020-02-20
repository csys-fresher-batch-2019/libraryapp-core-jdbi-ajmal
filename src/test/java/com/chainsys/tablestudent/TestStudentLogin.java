package com.chainsys.tablestudent;

import java.sql.Date;
import java.util.Scanner;

import com.chainsys.libraryapp.service.StudentDetailsService;

public class TestStudentLogin {

	public static void main(String[] args) throws Exception {
		StudentDetailsService ob=new StudentDetailsService();
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the Student Id");
		int studentId=sc.nextInt();
		System.out.print("Enter the Student DOB");
		String date=sc.next();
		Date dateOfBirth = Date.valueOf(date);
		Boolean out=ob.studentLogin(studentId, dateOfBirth);
		if(out) {
			System.out.println("LoggedIn");
		}
		sc.close();

	}

}
