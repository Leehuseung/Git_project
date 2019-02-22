package kr.co.bit_camp.vo;

import java.util.Date;
import java.util.List;

public class Message {
	private int no;
	private int status;                  //메세지상태   0,1,2   0일시에 안읽음,1일시에 읽음
	private String sendId;
	private String receiveId;
	private String sendName;
	private String receiveName;
	private String content;
	private String sendDel;
	private String receiveDel;
	private Date time;
	private String title;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSendId() {
		return sendId;
	}
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	public String getReceiveId() {
		return receiveId;
	}
	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}
	public String getSendName() {
		return sendName;
	}
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public String getReceiveName() {
		return receiveName;
	}
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendDel() {
		return sendDel;
	}
	public void setSendDel(String sendDel) {
		this.sendDel = sendDel;
	}
	public String getReceiveDel() {
		return receiveDel;
	}
	public void setReceiveDel(String receiveDel) {
		this.receiveDel = receiveDel;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	

}
