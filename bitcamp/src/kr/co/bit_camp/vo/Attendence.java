package kr.co.bit_camp.vo;

import java.util.Date;

public class Attendence {
	private int no;
	private int attStatus;
	private Date attEnterTime;
	private Date attLeaveTime;
	private int attNo;	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getAttStatus() {
		return attStatus;
	}
	public void setAttStatus(int attStatus) {
		this.attStatus = attStatus;
	}
	public Date getAttEnterTime() {
		return attEnterTime;
	}
	public void setAttEnterTime(Date attEnterTime) {
		this.attEnterTime = attEnterTime;
	}
	public Date getAttLeaveTime() {
		return attLeaveTime;
	}
	public void setAttLeaveTime(Date attLeaveTime) {
		this.attLeaveTime = attLeaveTime;
	}
	public int getAttNo() {
		return attNo;
	}
	public void setAttNo(int attNo) {
		this.attNo = attNo;
	}
	
}