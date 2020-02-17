package com.chainsys.libraryapp.dao;

import java.util.ArrayList;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.chainsys.libraryapp.LibaryModel.BookDetails;
import com.chainsys.libraryapp.dao.mapper.BookRowMapper;
import com.chainsys.libraryapp.exception.DbException;

public interface BookDetailsDAO {
	 
	@SqlUpdate("insert into books(book_name,book_cat,book_author,book_edition,no_of_bks,no_of_pgs,purchased_date,book_price,book_id)values  (?,?,?,?,?,?,?,?,id_seq.nextval)")
	public void addNewBook(BookDetails bookdetails)throws DbException;
	
	@SqlUpdate("update books set no_of_bks=(no_of_bks+:bookCopies) where book_id=:bookId")
	 public void updateBookCopies(@Bind("bookId")int bookId,@Bind("bookCopies")int bookCopies)throws DbException;
	 
	 @SqlQuery("select * from books where book_id=?")
	 @RegisterRowMapper(BookRowMapper.class)
	 public BookDetails displayBook(int bookId)throws DbException;
	 
	 @SqlQuery("select * from books")
	 @RegisterRowMapper(BookRowMapper.class)
	 public ArrayList<BookDetails> displayAllBooks()throws DbException;
	 

	 @SqlQuery("select * from books where lower(book_name)like lower('%'||?||'%')")
	 @RegisterRowMapper(BookRowMapper.class)
	 public ArrayList<BookDetails> searchByName(String bookName) throws DbException;

	 @SqlQuery("select 1 from books where book_id=?")	 
	public Integer findByBookId(int bookId);

}
