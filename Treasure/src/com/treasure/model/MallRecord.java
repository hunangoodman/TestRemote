package com.treasure.model;

import java.sql.Date;
/**
 * 吴健
 * @time 2018-2-1
 * */
public class MallRecord {
	private long mallId ;
	private double mallIntegral;
	private String mallAddress;
	private String mallStatus;
	private String mallCreatetime;
	private long memberId;
	private String fullName;
	private String userName;
	
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getMallId() {
		return mallId;
	}
	
	public String getMallCreatetime() {
		return mallCreatetime;
	}

	public void setMallCreatetime(String mallCreatetime) {
		this.mallCreatetime = mallCreatetime;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
	public void setMallId(long mallId) {
		this.mallId = mallId;
	}

	public double getMallIntegral() {
		return mallIntegral;
	}
	public void setMallIntegral(double mallIntegral) {
		this.mallIntegral = mallIntegral;
	}
	public String getMallAddress() {
		return mallAddress;
	}
	public void setMallAddress(String mallAddress) {
		this.mallAddress = mallAddress;
	}
	public String getMallStatus() {
		return mallStatus;
	}
	public void setMallStatus(String mallStatus) {
		this.mallStatus = mallStatus;
	}
	
}
