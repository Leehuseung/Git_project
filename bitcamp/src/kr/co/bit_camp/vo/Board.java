package kr.co.bit_camp.vo;

import java.util.Date;
import java.util.List;

import kr.co.bit_camp.vo.Comment;

public class Board {
	private int no;
	private int postNo; 
	private String postTitle;
	private String postWriter;
	private String postContent;
	private int postViewCnt;
	private Date postRegDate;
	private List<Comment> commentList;
	private int pageNo;
	private int rnum;
//	private double postPageNo;
	
	


	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	 
	public int getPostViewCnt() {
		return postViewCnt;
	}

	public void setPostViewCnt(int postViewCnt) {
		this.postViewCnt = postViewCnt;
	}

	public Date getPostRegDate() {
		return postRegDate;
	}
	
	public String getPostContent() {
		return postContent;
	}
	
	public String getPostWriter() {
		return postWriter;
	}
	
	public String getPostTitle() {
		return postTitle;
	}
	
	public int getPostNo() {
		return postNo;
	}
	
	public Board setPostRegDate(Date r) {
		postRegDate = r;
		return this;
	}
	
	public Board setPostContent(String c) {
		postContent = c;
		return this;
	}
	
	public Board setPostWriter(String w) {
		postWriter = w;
		return this;
	}
	
	public Board setPostTitle(String t) {
		postTitle = t;
		return this;
	}
	
	public void setPostNo(int n) {
		postNo = n;
	}
}












