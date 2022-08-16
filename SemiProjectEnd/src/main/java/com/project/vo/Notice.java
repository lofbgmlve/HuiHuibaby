package com.project.vo;

import java.sql.Date;

public class Notice {
	private int nt_no;
	private String nt_subject;
	private String nt_content;
	private String nt_image;
	private Date nt_date;
	private String mg_id;
	
	public Notice() {}
	public Notice(int nt_no, String nt_subject, String nt_content, 
			String nt_image, Date nt_date, String mg_id) {
		this.nt_no = nt_no;
		this.nt_subject = nt_subject;
		this.nt_content = nt_content;
		this.nt_image = nt_image;
		this.nt_date = nt_date;
		this.mg_id = mg_id;
	}
	public int getNt_no() {
		return nt_no;
	}
	public void setNt_no(int nt_no) {
		this.nt_no = nt_no;
	}
	public String getNt_subject() {
		return nt_subject;
	}
	public void setNt_subject(String nt_subject) {
		this.nt_subject = nt_subject;
	}
	public String getNt_content() {
		return nt_content;
	}
	public void setNt_content(String nt_content) {
		this.nt_content = nt_content;
	}
	public String getNt_image() {
		return nt_image;
	}
	public void setNt_image(String nt_image) {
		this.nt_image = nt_image;
	}
	public Date getNt_date() {
		return nt_date;
	}
	public void setNt_date(Date nt_date) {
		this.nt_date = nt_date;
	}
	public String getMg_id() {
		return mg_id;
	}
	public void setMg_id(String mg_id) {
		this.mg_id = mg_id;
	}
}
