package com.treasure.model;

import com.treasure.bean.TradeResult;

public class Trade {
	private Long id;

	private Double price;

	private Double amount;

	private String time;

	private Integer type;

	private Long sellerMemberId;

	private Long buyerMemberId;

	private Byte status;

	private String remark;

	private Long memberId;
	
	private String uuid;
	
	private String passwordTwo;

	// 无映射属性
	private String mobile;
	private String fullName;
	
	private Integer flag;

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getPasswordTwo() {
		return passwordTwo;
	}

	public void setPasswordTwo(String passwordTwo) {
		this.passwordTwo = passwordTwo;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public TradeResult validateParam(TradeResult result, int type) {
		result.setCode(1);
		switch (type) {
		case 1: // 买入
			if (price == null || price <= 0) {
				result.setCode(0);
				result.setErrorCode("E0701");
				result.setMsg("购买价格不能小于等于0!");
			}
			if (amount == null || amount <= 0) {
				result.setCode(0);
				result.setErrorCode("E0702");
				result.setMsg("购买数量不能小于等于0!");
			}
			break;
		case 2: // 卖出
			if (price == null || price <= 0) {
				result.setCode(0);
				result.setErrorCode("E0801");
				result.setMsg("卖出价格不能小于等于0!");
			}
			if (amount == null || amount <= 0) {
				result.setCode(0);
				result.setErrorCode("E0802");
				result.setMsg("卖出数量不能小于等于0!");
			}
			break;
		case 3: // 转让
			if (amount == null || amount <= 0) {
				result.setCode(0);
				result.setErrorCode("E1001");
				result.setMsg("转让数量不能小于等于0!");
			}
			break;
		default:
			break;
		}
		return result;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getSellerMemberId() {
		return sellerMemberId;
	}

	public void setSellerMemberId(Long sellerMemberId) {
		this.sellerMemberId = sellerMemberId;
	}

	public Long getBuyerMemberId() {
		return buyerMemberId;
	}

	public void setBuyerMemberId(Long buyerMemberId) {
		this.buyerMemberId = buyerMemberId;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "Trade [id=" + id + ", price=" + price + ", amount=" + amount
				+ ", time=" + time + ", type=" + type + ", sellerMemberId="
				+ sellerMemberId + ", buyerMemberId=" + buyerMemberId
				+ ", status=" + status + ", remark=" + remark + ", memberId="
				+ memberId + ", uuid=" + uuid + ", mobile=" + mobile
				+ ", fullName=" + fullName + "]";
	}
	

}