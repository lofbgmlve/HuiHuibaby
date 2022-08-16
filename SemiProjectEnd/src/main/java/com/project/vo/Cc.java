package com.project.vo;

import java.sql.Timestamp;

public class Cc {

	private int col5;
	private String col;
	private String col2;	
	private Timestamp col3;
	private String mg_id;
	private int cs_no;	
	
	public Cc() { }
	public Cc(int col5, String col, String col2, Timestamp col3, String mg_id, int cs_no) {
		this.col5 = col5;
		this.col= col;
		this.col2 = col2;
		this.col3 = col3;
		this.mg_id = mg_id;
		this.cs_no = cs_no;		
	}
	public int getCol5() {
		return col5;
	}
	public void setCol5(int col5) {
		this.col5 = col5;
	}
	public String getCol() {
		return col;
	}
	public void setCol(String col) {
		this.col = col;
	}
	public String getCol2() {
		return col2;
	}
	public void setCol2(String col2) {
		this.col2 = col2;
	}
	public Timestamp getCol3() {
		return col3;
	}
	public void setCol3(Timestamp col3) {
		this.col3 = col3;
	}
	public String getMg_id() {
		return mg_id;
	}
	public void setMg_id(String mg_id) {
		this.mg_id = mg_id;
	}
	public int getCs_no() {
		return cs_no;
	}
	public void setCs_no(int cs_no) {
		this.cs_no = cs_no;
	}
	
	
	
}
