package kr.co.bit_camp.vo;

import java.util.Date;

public class Comment {
	private int comNo;
	private int no;
	private int postNo;
	private String comWriter;
	private String comContent;
	private Date comRegDate;
	
	
	public int getComNo() {
		return comNo;
	}
	public void setComNo(int comNo) {
		this.comNo = comNo;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getComWriter() {
		return comWriter;
	}
	public void setComWriter(String comWriter) {
		this.comWriter = comWriter;
	}
	public String getComContent() {
		return comContent;
	}
	public void setComContent(String comContent) {
		this.comContent = comContent;
	}
	public Date getComRegDate() {
		return comRegDate;
	}
	public void setComRegDate(Date comRegDate) {
		this.comRegDate = comRegDate;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
}