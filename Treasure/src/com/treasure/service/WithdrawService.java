package com.treasure.service;

import java.util.List;

import com.treasure.bean.PageInfo;
import com.treasure.model.Withdraw;
import com.treasure.model.WithdrawExample;


public interface WithdrawService {

	int add(Withdraw withdraw);

	int selectCount(Withdraw withdraw);

	List<Withdraw> selectList(Withdraw withdraw, PageInfo pageInfo);

	Withdraw selectByKey(Long id);

	int check(Withdraw withdraw);

	int countByExample(WithdrawExample example);
}
