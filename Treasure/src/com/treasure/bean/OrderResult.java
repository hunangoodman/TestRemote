package com.treasure.bean;

import java.util.List;

import com.treasure.model.Order;

public class OrderResult extends Result{
	private Order order;
	private PageInfo pageInfo;
	private List<Order> orders;

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
