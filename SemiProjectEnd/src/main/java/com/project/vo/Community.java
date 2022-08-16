package com.project.vo;

import java.sql.Date;

public class Community {

	private int communityNo;
	private String communitySubject;
	private String communityContent;
	private String communityImage;
	private Date communityDate;
	private String nickName;
	
	public Community() { }
	public Community(int communityNo, String communitySubject, String communityContent, 
			String communityImage, Date communityDate, String nickName) {
		this.communityNo = communityNo;
		this.communitySubject = communitySubject;
		this.communityContent = communityContent;
		this.communityImage = communityImage;
		this.communityDate = communityDate; 
		this.nickName = nickName;
	}
	public int getCommunityNo() {
		return communityNo;
	}
	public void setCommunityNo(int communityNo) {
		this.communityNo = communityNo;
	}
	public String getCommunitySubject() {
		return communitySubject;
	}
	public void setCommunitySubject(String communitySubject) {
		this.communitySubject = communitySubject;
	}
	public String getCommunityContent() {
		return communityContent;
	}
	public void setCommunityContent(String communityContent) {
		this.communityContent = communityContent;
	}
	public String getCommunityImage() {
		return communityImage;
	}
	public void setCommunityImage(String communityImage) {
		this.communityImage = communityImage;
	}
	public Date getCommunityDate() {
		return communityDate;
	}
	public void setCommunityDate(Date communityDate) {
		this.communityDate = communityDate;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
}
	