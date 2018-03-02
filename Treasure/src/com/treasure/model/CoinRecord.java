package com.treasure.model;

public class CoinRecord {
	private Integer id;
	private long memberId;
	private String recordDate;
	private Double coin;
	private String menber;
	private String mobile;
	private String fullName;
	private String userName;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public long getMemberId() {
		return memberId;
	}
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	public Double getCoin() {
		return coin;
	}
	public void setCoin(Double double1) {
		this.coin = double1;
	}
	public String getMenber() {
		return menber;
	}
	public void setMenber(String menber) {
		this.menber = menber;
	}
	
	
}
