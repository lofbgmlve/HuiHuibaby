package com.project.vo;

import java.sql.Date;

//member 정의 (전에 단톡에 공지한대로 member대신 user라고 정의함)
public class User {
	
	// user 정보
	
	//아이디
	private String mId;
	// 패스워드
	private String mPw;
	// 닉네임
	private String mNickname;
	// 이름
	private String mName;
	// 휴대폰
	private String mPhone;
	// 성별
	private String mGender;
	// 이메일
	private String mEmail;
	// 생일
	private String mBirth;
	// 가입일
	private Date mRegistdate;
	private String mRole;
	
	public User() {}
	public User(String mId,  String mPw, String mNickname,String mName, String mPhone,
			String mGender, String mEmail, String mBirth, Date mRegistdate , String mRole) {
		this.mId = mId;
		this.mPw = mPw;
		this.mNickname = mNickname;
		this.mName = mName;
		this.mGender = mGender;
		this.mPhone = mPhone;
		this.mEmail = mEmail;
		this.mBirth = mBirth;
		this.mRegistdate = mRegistdate;
		this.mRole = mRole;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmPw() {
		return mPw;
	}
	public void setmPw(String mPw) {
		this.mPw = mPw;
	}
	public String getmNickname() {
		return mNickname;
	}
	public void setmNickname(String mNickname) {
		this.mNickname = mNickname;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmPhone() {
		return mPhone;
	}
	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}
	public String getmGender() {
		return mGender;
	}
	public void setmGender(String mGender) {
		this.mGender = mGender;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public String getmBirth() {
		return mBirth;
	}
	public void setmBirth(String mBirth) {
		this.mBirth = mBirth;
	}
	public Date getmRegistdate() {
		return mRegistdate;
	}
	public void setmRegistdate(Date mRegistdate) {
		this.mRegistdate = mRegistdate;
	}
	public String getmRole() {
		return mRole;
	}
	public void setmRole(String mRole) {
		this.mRole = mRole;
	}
	
	
	
}
