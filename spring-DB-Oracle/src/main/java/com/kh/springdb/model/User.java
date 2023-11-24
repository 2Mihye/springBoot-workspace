package com.kh.springdb.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User {
	private int mno; // 캡슐화
	private String mname;
	private String memail;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date mbirth = new Date();
	
	public void setMno(int mno) {
		this.mno = mno;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public void setMbirth(Date mbirth) {
		this.mbirth = mbirth;
	}

	public int getMno() {
		return mno;
	}
	public String getMname() {
		return mname;
	}
	public String getMemail() {
		return memail;
	}
	public Date getMbirth() {
		return mbirth;
	}

}
