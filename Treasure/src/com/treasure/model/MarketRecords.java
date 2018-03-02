package com.treasure.model;

public class MarketRecords {
	private long marketId ;
	private double marketIntegral;
	private String marketAddress;
	private String marketStatus;
	private String marketCreatetime;
	private long memberId;
	private String fullName;
	private String userName;
	public long getMarketId() {
		return marketId;
	}
	public void setMarketId(long marketId) {
		this.marketId = marketId;
	}
	public double getMarketIntegral() {
		return marketIntegral;
	}
	public void setMarketIntegral(double marketIntegral){
		this.marketIntegral = marketIntegral;
	}
	public String getMarketAddress() {
		return marketAddress;
	}
	public void setMarketAddress(String marketAddress) {
		this.marketAddress = marketAddress;
	}
	public String getMarketStatus() {
		return marketStatus;
	}
	public void setMarketStatus(String marketStatus) {
		this.marketStatus = marketStatus;
	}
	public String getMarketCreatetime() {
		return marketCreatetime;
	}
	public void setMarketCreatetime(String marketCreatetime) {
		this.marketCreatetime = marketCreatetime;
	}
	public long getMemberId() {
		return memberId;
	}
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
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
	
}
