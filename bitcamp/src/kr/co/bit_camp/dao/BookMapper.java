package kr.co.bit_camp.dao;

import java.util.List;

import kr.co.bit_camp.vo.Book;
import kr.co.bit_camp.vo.Hopebook;
import kr.co.bit_camp.vo.Rental;
import kr.co.bit_camp.vo.User;
import kr.co.bit_camp.vo.UserRental;

public interface BookMapper {
	
	List<Book> selectBookList();
	void insertBook(Book book);
	List<Book> selectDetailList(String bookTitle);
	List<Book> selectDetailList1(String bookWriter);
	void insertHopeBook(Hopebook hopebook);
	List<Hopebook> selectHopeBookList();
	void insertRental(Rental rental);
	List<Rental> selectRental();
	void updateReturnRental(Rental rental);
	List<Rental> selectRentalList(int no);
	List<UserRental> selectDetailRentList(int no);
	void deleteBook(int no);
	List<Book> selectBestList();
	void deleteHopeBook(int no);
	List<User> selectRentalBestList();
	
	

}
