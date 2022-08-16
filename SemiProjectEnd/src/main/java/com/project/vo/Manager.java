package com.project.vo;

public class Manager {

	private String mg_id;
	private String mg_pw;
	private String mg_name;
	private int mg_num;
	
	public Manager() {}
	public Manager(String mg_id, String mg_pw, String mg_name, int mg_num) {
		this.mg_id = mg_id;
		this.mg_pw = mg_pw;
		this.mg_name = mg_pw;
		this.mg_num = mg_num;
	}
	public String getMg_id() {
		return mg_id;
	}
	public void setMg_id(String mg_id) {
		this.mg_id = mg_id;
	}
	public String getMg_pw() {
		return mg_pw;
	}
	public void setMg_pw(String mg_pw) {
		this.mg_pw = mg_pw;
	}
	public String getMg_name() {
		return mg_name;
	}
	public void setMg_name(String mg_name) {
		this.mg_name = mg_name;
	}
	public int getMg_num() {
		return mg_num;
	}
	public void setMg_num(int mg_num) {
		this.mg_num = mg_num;
	}
}
