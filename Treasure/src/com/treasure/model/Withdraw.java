package com.treasure.model;

import java.util.Date;

public class Withdraw {
	private Long id;

	private Long memberId;

	private Double amount;

	private String cardNumber;

	private Double procedureRates;

	private Double procedureMoney;

	private Double realityMoney;

	private Date applyTime;

	private Byte checkStatus;

	private Date checkTime;

	private String remark;
	// 无映射属性
	private String fullName;
	private String mobile;
	private String applyTime1;
	private String cardNumber1;
	private String bank;
	
	private String passwordTwo;

	
	public String getPasswordTwo() {
		return passwordTwo;
	}

	public void setPasswordTwo(String passwordTwo) {
		this.passwordTwo = passwordTwo;
	}

	public String getCardNumber1() {
		return cardNumber1;
	}

	public void setCardNumber1(String cardNumber1) {
		this.cardNumber1 = cardNumber1;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getApplyTime1() {
		return applyTime1;
	}

	public void setApplyTime1(String applyTime1) {
		this.applyTime1 = applyTime1;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Double getProcedureRates() {
		return procedureRates;
	}

	public void setProcedureRates(Double procedureRates) {
		this.procedureRates = procedureRates;
	}

	public Double getProcedureMoney() {
		return procedureMoney;
	}

	public void setProcedureMoney(Double procedureMoney) {
		this.procedureMoney = procedureMoney;
	}

	public Double getRealityMoney() {
		return realityMoney;
	}

	public void setRealityMoney(Double realityMoney) {
		this.realityMoney = realityMoney;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Byte getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Byte checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Withdraw [id=" + id + ", memberId=" + memberId + ", amount="
				+ amount + ", cardNumber=" + cardNumber + ", procedureRates="
				+ procedureRates + ", procedureMoney=" + procedureMoney
				+ ", realityMoney=" + realityMoney + ", applyTime=" + applyTime
				+ ", checkStatus=" + checkStatus + ", checkTime=" + checkTime
				+ ", remark=" + remark + ", fullName=" + fullName + ", mobile="
				+ mobile + ", applyTime1=" + applyTime1 + ", cardNumber1="
				+ cardNumber1 + ", bank=" + bank + "]";
	}
	
}