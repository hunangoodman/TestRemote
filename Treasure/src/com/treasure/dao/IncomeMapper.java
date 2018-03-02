package com.treasure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.treasure.model.InestmentIncome;
public interface IncomeMapper {
	
	public int insertInestment(@Param("record")InestmentIncome inestmentIncome);
	
	public int updateInterst(@Param("record")InestmentIncome inestmentIncome);
	
	public List<InestmentIncome> selectInterestRate(@Param("record")InestmentIncome inestmentIncome);
	
	public List<InestmentIncome> selectInterestAll();
}
