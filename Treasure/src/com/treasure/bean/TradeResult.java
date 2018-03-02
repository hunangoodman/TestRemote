package com.treasure.bean;

import java.util.List;
import java.util.Map;

import com.treasure.model.Trade;

public class TradeResult extends Result {

	private Trade trade;
	private List<Trade> trades;
	private PageInfo pageInfo;
	private Map<Object, Object> map;

	public Map<Object, Object> getMap() {
		return map;
	}

	public void setMap(Map<Object, Object> map) {
		this.map = map;
	}

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public List<Trade> getTrades() {
		return trades;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
