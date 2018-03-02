package com.treasure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.treasure.bean.PageInfo;
import com.treasure.model.Withdraw;
import com.treasure.model.WithdrawExample;

public interface WithdrawMapper {
	int countByExample(WithdrawExample example);

	int deleteByExample(WithdrawExample example);

	int deleteByPrimaryKey(Long id);

	int insert(Withdraw record);

	int insertSelective(Withdraw record);

	List<Withdraw> selectByExample(WithdrawExample example);

	Withdraw selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") Withdraw record, @Param("example") WithdrawExample example);

	int updateByExample(@Param("record") Withdraw record, @Param("example") WithdrawExample example);

	int updateByPrimaryKeySelective(Withdraw record);

	int updateByPrimaryKey(Withdraw record);

	int selectCount(@Param("record") Withdraw withdraw);

	List<Withdraw> selectList(@Param("record") Withdraw withdraw, @Param("pageInfo") PageInfo pageInfo);

	Withdraw selectByKey(@Param("id") Long id);
}