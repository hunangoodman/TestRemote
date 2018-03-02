package com.treasure.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.treasure.bean.PageInfo;
import com.treasure.dao.ModuleMapper;
import com.treasure.dao.MoneyManagementMapper;
import com.treasure.model.InestmentIncome;
import com.treasure.model.MoneyManagement;
import com.treasure.service.IncomeService;
import com.treasure.service.MoneyManagementService;

@Service(value = "moneyManagementServiceImpl")
public class MoneyManagementServiceImpl implements MoneyManagementService{
	@Autowired
	private MoneyManagementMapper moneyManagementMapper;
	
	@Autowired
	@Qualifier(value = "incomeServiceImpl")
	private IncomeService incomeService;
	
	public int insert(MoneyManagement moneyManagement) {
		moneyManagementMapper.insert(moneyManagement);
		InestmentIncome inestmentIncome = new InestmentIncome();
		inestmentIncome.setIncomeId(moneyManagement.getMemberId()); 
		incomeService.selectimcomeId(inestmentIncome);
		return 1;
	}
	public List<MoneyManagement> selectTurninto(MoneyManagement moneyManagement, PageInfo pageInfo) {
		return moneyManagementMapper.selectTurninto(moneyManagement, pageInfo);
	}
	public int selectTurnintoTotal(MoneyManagement moneyManagement) {
		return moneyManagementMapper.selectTurnintoTotal(moneyManagement);
	}
	@Override
	public List<MoneyManagement> selectAll() {
		// TODO Auto-generated method stub
		return moneyManagementMapper.selectAll();
	}
	
}
