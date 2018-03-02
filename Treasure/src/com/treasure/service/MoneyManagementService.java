package com.treasure.service;

import java.util.List;

import com.treasure.bean.PageInfo;
import com.treasure.model.MoneyManagement;

public interface MoneyManagementService {
	
	public int insert(MoneyManagement moneyManagement);
	
	public List<MoneyManagement> selectTurninto(MoneyManagement moneyManagement, PageInfo pageInfo);
	
	public int selectTurnintoTotal(MoneyManagement moneyManagement);
	
	public List<MoneyManagement> selectAll();
}
