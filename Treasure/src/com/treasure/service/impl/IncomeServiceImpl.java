package com.treasure.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.treasure.dao.IncomeMapper;
import com.treasure.model.InestmentIncome;
import com.treasure.scheduled.PriceScheduled;
import com.treasure.service.IncomeService;
@Service
public class IncomeServiceImpl implements IncomeService{
	
	@Autowired
	IncomeMapper inestmentIncomeMapper;
	
	@Value("${unfreeze.integral.days}")
	private Integer unfreezeDays;
	
	@Value("${unfreeze.integral.rate}")
	private Double unfreezeRate;
	
	public int insertIncome(InestmentIncome inestmentIncome) {
		return inestmentIncomeMapper.insertInestment(inestmentIncome);
	}

	@Override
	public int updateIncome(InestmentIncome inestmentIncome) {
		return inestmentIncomeMapper.updateInterst(inestmentIncome);
	}

	public List<InestmentIncome> selectimcomeId(InestmentIncome inestmentIncome) {
		return inestmentIncomeMapper.selectInterestRate(inestmentIncome);
		//List<InestmentIncome> inest = inestmentIncomeMapper.selectInterestAll();
		//return inest;
	}

}
