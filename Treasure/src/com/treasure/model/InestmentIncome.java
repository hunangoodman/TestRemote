package com.treasure.model;

import java.util.Date;
/**
 * 收益记录
 * @time 2018-2-8
 * */
public class InestmentIncome {
	private long   incomeId; 
	private long   investmentId;
	private long   memberId;
	private double totalMoney;
	private double incomeMoney;
	private String financialmanagement;
	private String incomeType;
	private Date   createTime;
	
	
	
	public String getFinancialmanagement() {
		return financialmanagement;
	}
	public void setFinancialmanagement(String financialmanagement) {
		this.financialmanagement = financialmanagement;
	}
	public long getMemberId() {
		return memberId;
	}
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
	public String getIncomeType() {
		return incomeType;
	}
	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}
	public long getIncomeId() {
		return incomeId;
	}
	public void setIncomeId(long incomeId) {
		this.incomeId = incomeId;
	}
	public long getInvestmentId() {
		return investmentId;
	}
	public void setInvestmentId(long investmentId) {
		this.investmentId = investmentId;
	}
	public double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public double getIncomeMoney() {
		return incomeMoney;
	}
	public void setIncomeMoney(double incomeMoney) {
		this.incomeMoney = incomeMoney;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
