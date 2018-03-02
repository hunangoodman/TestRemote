package com.treasure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.treasure.bean.PageInfo;
import com.treasure.model.MoneyManagement;



public interface MoneyManagementMapper {
	public int insert(@Param("record")MoneyManagement MoneyManagement);
	public List<MoneyManagement> selectTurninto(@Param("record")MoneyManagement moneyManagement,@Param("pageInfo")PageInfo pageInfo);
	public int selectTurnintoTotal(@Param("record")MoneyManagement moneyManagement);
	public List<MoneyManagement> selectAll();
}
