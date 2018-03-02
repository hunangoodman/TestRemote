package com.treasure.model;

import java.util.Date;

public class Order {
	private Long id;

	private Long memberId;

	private String mobile;

	private String orderNo;

	private Double price;

	private Integer quantity;

	private Double integral;

	private Integer payType;

	private String fullName;

	private Double totalAmount;

	private Double rechargeAmount;

	private String payNumber;

	private Date addTime;

	private Byte status;

	private Byte payStatus;

	private String payOrderNo;

	private String checkMsg;

	private Date checkTime;

	private String remark;
	// 无映射属性
	private String payType1;
	private String address;
	private String sysTime;
	private String amount;
	private String uuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSysTime() {
		return sysTime;
	}

	public void setSysTime(String sysTime) {
		this.sysTime = sysTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPayType1() {
		return payType1;
	}

	public void setPayType1(String payType1) {
		this.payType1 = payType1;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getIntegral() {
		return integral;
	}

	public void setIntegral(Double integral) {
		this.integral = integral;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(Double rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public String getPayNumber() {
		return payNumber;
	}

	public void setPayNumber(String payNumber) {
		this.payNumber = payNumber;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Byte payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}

	public String getCheckMsg() {
		return checkMsg;
	}

	public void setCheckMsg(String checkMsg) {
		this.checkMsg = checkMsg;
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
		return "Order [id=" + id + ", memberId=" + memberId + ", mobile="
				+ mobile + ", orderNo=" + orderNo + ", price=" + price
				+ ", quantity=" + quantity + ", integral=" + integral
				+ ", payType=" + payType + ", fullName=" + fullName
				+ ", totalAmount=" + totalAmount + ", rechargeAmount="
				+ rechargeAmount + ", payNumber=" + payNumber + ", addTime="
				+ addTime + ", status=" + status + ", payStatus=" + payStatus
				+ ", payOrderNo=" + payOrderNo + ", checkMsg=" + checkMsg
				+ ", checkTime=" + checkTime + ", remark=" + remark
				+ ", payType1=" + payType1 + ", address=" + address
				+ ", sysTime=" + sysTime + ", amount=" + amount + ", uuid="
				+ uuid + "]";
	}

}