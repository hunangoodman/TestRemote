package com.treasure.service;

import java.util.List;

import com.treasure.model.InestmentIncome;
public interface IncomeService {
	public int insertIncome(InestmentIncome inestmentIncome);
	
	public int updateIncome(InestmentIncome inestmentIncome);
	
	public List<InestmentIncome> selectimcomeId(InestmentIncome inestmentIncome);
}
