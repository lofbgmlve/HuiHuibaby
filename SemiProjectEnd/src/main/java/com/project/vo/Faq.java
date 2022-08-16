package com.project.vo;

public class Faq {

	private int faq_no;
	private String faq_title;
	private String faq_image;	
	private String faq_content;
	private String mg_id;	
	
	public Faq() { }
	public Faq(int faq_no, String faq_title, String faq_image, String faq_content, String mg_id) {
		this.faq_no = faq_no;
		this.faq_image = faq_image;
		this.faq_title = faq_title;
		this.faq_content = faq_content;
		this.mg_id = mg_id;		
	}
	public int getFaq_no() {
		return faq_no;
	}
	public void setFaq_no(int faq_no) {
		this.faq_no = faq_no;
	}
	public String getFaq_title() {
		return faq_title;
	}
	public void setFaq_title(String faq_title) {
		this.faq_title = faq_title;
	}
	public String getFaq_image() {
		return faq_image;
	}
	public void setFaq_image(String faq_image) {
		this.faq_image = faq_image;
	}
	public String getFaq_content() {
		return faq_content;
	}
	public void setFaq_content(String faq_content) {
		this.faq_content = faq_content;
	}
	public String getMg_id() {
		return mg_id;
	}
	public void setMg_id(String mg_id) {
		this.mg_id = mg_id;
	}
	
	
}
