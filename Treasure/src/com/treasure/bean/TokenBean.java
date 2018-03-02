package com.treasure.bean;

import java.util.Date;

public class TokenBean {
	private Long memberId;
	private Date date;
	
	public TokenBean(Long memberId, Date date) {
		super();
		this.memberId = memberId;
		this.date = date;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
