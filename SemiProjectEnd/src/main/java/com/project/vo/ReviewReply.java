package com.project.vo;

import java.sql.Timestamp;

public class ReviewReply {
	private int rrNo;
	private String rrContent;
	private Timestamp rrDate;
	private String rrNickname;
	
	public ReviewReply() { }
	public ReviewReply(int rrNo, String rrContent, Timestamp rrDate, 
				String rrNickname) {
		this.rrNo = rrNo;
		this.rrContent = rrContent;
		this.rrDate = rrDate;
		this.rrNickname = rrNickname;
		
		
	}
	public int getRrNo() {
		return rrNo;
	}
	public void setRrNo(int rrNo) {
		this.rrNo = rrNo;
	}
	public String getRrContent() {
		return rrContent;
	}
	public void setRrContent(String rrContent) {
		this.rrContent = rrContent;
	}
	public Timestamp getRrDate() {
		return rrDate;
	}
	public void setRrDate(Timestamp rrDate) {
		this.rrDate = rrDate;
	}
	public String getRrNickname() {
		return rrNickname;
	}
	public void setRrNickname(String rrNickname) {
		this.rrNickname = rrNickname;
	}
	
}
