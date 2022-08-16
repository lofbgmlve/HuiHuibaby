package com.project.vo;

import java.sql.Timestamp;

public class Cs {

	private int cs_no;
	private String cs_subject;
	private String cs_content;	
	private String cs_image;
	private Timestamp cs_date;
	private String m_nickname;	
	
	public Cs() { }
	public Cs(int cs_no, String cs_subject, String cs_content, String cs_image, Timestamp cs_date, String m_nickname) {
		this.cs_no = cs_no;
		this.cs_subject = cs_subject;
		this.cs_content = cs_content;
		this.cs_image = cs_image;
		this.cs_content = cs_content;
		this.m_nickname = m_nickname;		
	}
	
	public int getCs_no() {
		return cs_no;
	}
	public void setCs_no(int cs_no) {
		this.cs_no = cs_no;
	}
	public String getCs_subject() {
		return cs_subject;
	}
	public void setCs_subject(String cs_subject) {
		this.cs_subject = cs_subject;
	}
	public String getCs_content() {
		return cs_content;
	}
	public void setCs_content(String cs_content) {
		this.cs_content = cs_content;
	}
	public String getCs_image() {
		return cs_image;
	}
	public void setCs_image(String cs_image) {
		this.cs_image = cs_image;
	}
	public Timestamp getCs_date() {
		return cs_date;
	}
	public void setCs_date(Timestamp cs_date) {
		this.cs_date = cs_date;
	}
	public String getM_nickname() {
		return m_nickname;
	}
	public void setM_nickname(String m_nickname) {
		this.m_nickname = m_nickname;
	}
	
	
}
