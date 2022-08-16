package com.project.vo;

import java.util.Date;

public class Product {

	//p_no, p_title, p_name, p_part, p_status, p_way, p_date
	//p_price, p_pro,p_condition,p_file1,p_file2,p_file3,p_dateil,p_nickname
		
	
	private int no ;
	private String title;
	private String name; 
	private String part;
	private String staus;
	private String way;
	private Date date;
	private int price;
	private String pro;
	private String condition;
	private String file1;
	private String file2;
	private String file3;
	private String detail;
	private String nickname;
	private int recommend;
	
	
	
	public Product() {}
	
	public Product (int no, String title, String name, String part, String staus, String way,
							Date date, int price, String pro, String condition, String file1, String file2, String file3, String  detail,String nickname, int recommend) {
		this.no = no;
		this.title=title;
		this.name = name;
		this.part=part;
		this.staus = staus;
		this.way=way;
		this.date=date;
		this.price=price;
		this.pro=pro;
		this.condition=condition;
		this.file1=file1;
		this.file2=file2;
		this.file3=file3;
		this.detail=detail;
		this.nickname=nickname;
		this.recommend=recommend;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getStaus() {
		return staus;
	}

	public void setStaus(String staus) {
		this.staus = staus;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPro() {
		return pro;
	}

	public void setPro(String pro) {
		this.pro = pro;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}

	public String getFile2() {
		return file2;
	}

	public void setFile2(String file2) {
		this.file2 = file2;
	}

	public String getFile3() {
		return file3;
	}

	public void setFile3(String file3) {
		this.file3 = file3;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	
	
	
	
	
	
	
	
	
}
