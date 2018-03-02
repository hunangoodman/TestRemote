package com.treasure.bean;

import com.treasure.model.Order;

public class SignResult extends Result {
	private String sign;
	private Order order;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
