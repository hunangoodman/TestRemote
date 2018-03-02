package com.treasure.model;

import java.util.Date;

public class MoneyManagement {
	private long managementId;
	private String financialManagement;
	private String investMentperiod;
	private long memberId;
	private Date cretateTime;
	private double money;
	private String type;
	private String endTime;
	
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public long getManagementId() {
		return managementId;
	}
	public void setManagementId(long managementId){
		this.managementId = managementId;
	}
	public String getFinancialManagement() {
		return financialManagement;
	}
	public void setFinancialManagement(String financialManagement){
		this.financialManagement = financialManagement;
	}
	public String getInvestMentperiod() {
		return investMentperiod;
	}
	public void setInvestMentperiod(String investMentperiod){
		this.investMentperiod = investMentperiod;
	}
	public long getMemberId() {
		return memberId;
	}
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
	public Date getCretateTime() {
		return cretateTime;
	}
	public void setCretateTime(Date cretateTime) {
		this.cretateTime = cretateTime;
	}
		
}
