package kr.co.bit_camp.vo;

import java.util.Date;

public class Rental {
	private int no;      // 회원번호
	private int rentalNo; // 렌탈 번호
	private int bookNo;  // 책번호
	private int rentalRent; // 대여여부
	private Date rentalInDate;   // 빌린 날짜
	private Date rentalOutDate;  // 반납 날짜
	
	public Date getRentalInDate() {
		return rentalInDate;
	}
	public void setRentalInDate(Date rentalInDate) {
		this.rentalInDate = rentalInDate;
	}
	public Date getRentalOutDate() {
		return rentalOutDate;
	}
	public void setRentalOutDate(Date rentalOutDate) {
		this.rentalOutDate = rentalOutDate;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getRentalNo() {
		return rentalNo;
	}
	public void setRentalNo(int rentalNo) {
		this.rentalNo = rentalNo;
	}
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public int getRentalRent() {
		return rentalRent;
	}
	public void setRentalRent(int rentalRent) {
		this.rentalRent = rentalRent;
	}
	
	
	

}
