package com.treasure.bean;

import java.util.List;

import com.treasure.model.Withdraw;

public class WithdrawResult extends Result {
	private List<Withdraw> withdraws;
	private PageInfo pageInfo;

	public List<Withdraw> getWithdraws() {
		return withdraws;
	}

	public void setWithdraws(List<Withdraw> withdraws) {
		this.withdraws = withdraws;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
}
