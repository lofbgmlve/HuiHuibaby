package com.project.vo;

import java.sql.Date;

//review 정의
public class Review {
	private int rNo;
	private String rSubject;
	private String rContent;
	private String rImage;
	private Date rDate;
	private String rScore;
	private int pNo;
	private String rNickname;
	
	public Review() {}
	public Review(int rNo, String rSubject, String rContent, String rImage,
			 Date rDate, String rScore, int pNo, String rNickname) {
		this.pNo = pNo;
		this.rContent = rContent;
		this.rDate = rDate;
		this.rImage = rImage;
		this.rNickname = rNickname;
		this.rNo = rNo;
		this.rScore = rScore;
		this.rSubject = rSubject;
	}
	public int getrNo() {
		return rNo;
	}
	public void setrNo(int rNo) {
		this.rNo = rNo;
	}
	public String getrSubject() {
		return rSubject;
	}
	public void setrSubject(String rSubject) {
		this.rSubject = rSubject;
	}
	public String getrContent() {
		return rContent;
	}
	public void setrContent(String rContent) {
		this.rContent = rContent;
	}
	public String getrImage() {
		return rImage;
	}
	public void setrImage(String rImage) {
		this.rImage = rImage;
	}
	public Date getrDate() {
		return rDate;
	}
	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}
	public String getrScore() {
		return rScore;
	}
	public void setrScore(String rScore) {
		this.rScore = rScore;
	}
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public String getrNickname() {
		return rNickname;
	}
	public void setrNickname(String rNickname) {
		this.rNickname = rNickname;
	}
	
	
}
