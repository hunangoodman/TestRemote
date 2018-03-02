package com.treasure.bean;

import java.util.List;

import com.treasure.model.PriceRecord;

public class PriceResult extends Result {

	private List<PriceRecord> prices;

	public List<PriceRecord> getPrices() {
		return prices;
	}

	public void setPrices(List<PriceRecord> prices) {
		this.prices = prices;
	}

}
