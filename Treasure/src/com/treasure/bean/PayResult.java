package com.treasure.bean;

public class PayResult {
	private String out_trade_no;
	private String trade_no;
	private Double total_amount;
	private String seller_id;
	private String timestamp;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public Double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(Double total_amount) {
		this.total_amount = total_amount;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	@Override
	public String toString() {
		return "PayResult [out_trade_no=" + out_trade_no + ", trade_no=" + trade_no + ", total_amount=" + total_amount + ", seller_id=" + seller_id + "]";
	}
}
