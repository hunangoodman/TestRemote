package com.treasure.service;

import java.util.List;

import com.treasure.bean.PageInfo;
import com.treasure.model.CoinRecord;
import com.treasure.model.Member;


public interface CoinRecordService {
	/*
	 * 查找所有
	 */
	public List<CoinRecord> findAll(CoinRecord cord, PageInfo pageVo);
	/*
	 * 按名字查找
	 */
	public List<CoinRecord> findByName(String name);
	
	public int selectCount(CoinRecord member);
	
	public void addCoinRecode(CoinRecord cord);
}
