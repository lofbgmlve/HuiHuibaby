package com.project.vo;

import java.sql.Timestamp;

public class CommunityReply {
	private int communityNo;
	private int crNo;
	private String crContent;
	private Timestamp crDate;
	private String cNickName;
	
	public CommunityReply() { }
	public CommunityReply(int crNo, String crContent, String cNickname) {
		this.crNo = crNo;
		this.crContent = crContent;
		this.cNickName = cNickname;
	}
	
	public CommunityReply(int communityNo, int crNo, String crContent, Timestamp crDate, String cNickname) {
	
		this.communityNo = communityNo;
		this.crNo = crNo;
		this.crContent = crContent;
		this.cNickName = cNickname;
	}
	public int getCommunityNo() {
		return communityNo;
	}
	public void setCommunityNo(int communityNo) {
		this.communityNo = communityNo;
	}
	public int getCrNo() {
		return crNo;
	}
	public void setCrNo(int crNo) {
		this.crNo = crNo;
	}
	public String getCrContent() {
		return crContent;
	}
	public void setCrContent(String crContent) {
		this.crContent = crContent;
	}
	public Timestamp getCrDate() {
		return crDate;
	}
	public void setCrDate(Timestamp crDate) {
		this.crDate = crDate;
	}
	public String getcNickName() {
		return cNickName;
	}
	public void setcNickName(String cNickName) {
		this.cNickName = cNickName;
	}
	
	
}
