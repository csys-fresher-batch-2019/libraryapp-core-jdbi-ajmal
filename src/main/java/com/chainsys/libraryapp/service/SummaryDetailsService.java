package com.chainsys.libraryapp.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.jdbi.v3.core.Jdbi;

import com.chainsys.libraryapp.LibaryModel.StudentFineSummaryDetails;
import com.chainsys.libraryapp.LibaryModel.SummaryDetailsDueDate;
import com.chainsys.libraryapp.LibaryModel.SummaryDetailsStudentDetails;
import com.chainsys.libraryapp.Util.Connectionutil;
import com.chainsys.libraryapp.dao.BookDetailsDAO;
import com.chainsys.libraryapp.dao.StudentDetailsDAO;
import com.chainsys.libraryapp.dao.SummaryDetailsDAO;

public class SummaryDetailsService {
	static Jdbi jdbi = Connectionutil.getJdbi();
	static SummaryDetailsDAO summaryDetailsDAO = jdbi.onDemand(SummaryDetailsDAO.class);
	static BookDetailsDAO bookDetailsDAO = jdbi.onDemand(BookDetailsDAO.class);
	static StudentDetailsDAO studentDetailsDAO = jdbi.onDemand(StudentDetailsDAO.class);
	
	// private SummaryDetailsDAO summaryDetailsDAO=new SummaryDetailsDAOImp();

	public boolean addNewEntry(int studentId, int bookId) throws Exception {

		Integer cont = bookDetailsDAO.findByBookId(bookId);

		// if invalid bookId throw exception
		if (cont == null) {
			throw new Exception("Invalid Book Id");
		}
		Integer contt = studentDetailsDAO.findByStudentId(studentId);
		if (contt == null) {
			throw new Exception("Invalid Student Id");
		}
			
		boolean bookTaken = bookTaken(studentId, bookId);
		if (bookTaken) {
			return summaryDetailsDAO.addNewEntry(studentId, bookId);
		}

		return bookTaken;
	}

	public Integer calculateFineAmount(int studentId, int bookId) throws Exception {
		boolean bookTaken = bookTaken(studentId, bookId);
		Integer fine = 0;
		if (bookTaken) {
			throw new Exception("You have not taken book");
		}
		LocalDate dueDate = summaryDetailsDAO.calculateFineAmount(studentId, bookId);
		LocalDate returnedDate = LocalDate.now();
		if (returnedDate.isAfter(dueDate)) {
			int diffInDays = (int) ChronoUnit.DAYS.between(dueDate, returnedDate);
			fine = diffInDays * 2;

		} else {
			// System.out.println("No Due Pending");
			fine = 0;
		}
		return fine;
	}

	public void updateReturnRecord(int studentId, int bookId, Integer fineAmount) throws Exception {
		summaryDetailsDAO.updateReturnRecord(studentId, bookId, fineAmount);
	}

	public ArrayList<SummaryDetailsDueDate> displayStudentNotReturnedBook(int bookId) throws Exception {
		Integer cont = bookDetailsDAO.findByBookId(bookId);
		if (cont == null) {
			throw new Exception("Invalid Book Id");
		}
		return summaryDetailsDAO.displayStudentNotReturnedBook(bookId);
	}

	public int totalFineAmount() throws Exception {
		return summaryDetailsDAO.totalFineAmount();
	}

	public ArrayList<SummaryDetailsStudentDetails> studentNotReturnedBook(int studentId) throws Exception {
		Integer std=studentDetailsDAO.findByStudentId(studentId);
		if(std==null)
		{
			throw new Exception("Invalid Student Id");
		}
		return summaryDetailsDAO.studentNotReturnedBook(studentId);
	}

	public boolean bookTaken(int studentId, int bookId) throws Exception {
		Integer bookTaken = summaryDetailsDAO.bookTaken(studentId, bookId);
		return bookTaken != null;
	}

	public ArrayList<StudentFineSummaryDetails> totalFineAmountOfStudent(int studentId) throws Exception {
		return summaryDetailsDAO.totalFineAmountOfStudent(studentId);
	}

	public Integer noOfBooksAvailable(int bookId) throws Exception {
		Integer cont = bookDetailsDAO.findByBookId(bookId);
		if (cont == null) {
			throw new Exception("Invalid Book Id");
		}
		return summaryDetailsDAO.noOfBooksAvailable(bookId);
	}

	public void limitForStudent(int studentId) throws Exception {
		Integer std=studentDetailsDAO.findByStudentId(studentId);
		if(std==null)
		{
			throw new Exception("Invalid Student Id");
		}
		int limit = summaryDetailsDAO.limitForStudent(studentId);
		if(limit>5)
		{
			throw new Exception("You limit for talking the book has Exceeded");
		}
		
	}

}
