package com.treasure.bean;

public class AjaxResult  extends Result{

	private double count;

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "AjaxResult [count=" + count + "]";
	}
	
	
}
