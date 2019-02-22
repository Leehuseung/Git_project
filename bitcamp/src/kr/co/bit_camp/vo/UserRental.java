package kr.co.bit_camp.vo;

import java.util.Date;

public class UserRental {
	private int bookNo;
	private int rentalRent;
	private String bookTitle;
	private String bookWriter;
	private String returnDate;
	private Date rentalInDate;
	
	public Date getRentalInDate() {
		return rentalInDate;
	}
	public void setRentalInDate(Date rentalInDate) {
		this.rentalInDate = rentalInDate;
	}
	public int getRentalRent() {
		return rentalRent;
	}
	public void setRentalRent(int rentalRent) {
		this.rentalRent = rentalRent;
	}
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookWriter() {
		return bookWriter;
	}
	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

}
