package com.treasure.service;

import java.util.List;

import com.treasure.bean.PageInfo;
import com.treasure.model.Trade;
import com.treasure.model.TradeExample;

public interface TradeService {
	
	List<Trade> queryTradeInfo(String memberId,PageInfo pageInfo);
	
	int addSendInfo(Trade trade);

	List<Trade> selectList(Trade trade, PageInfo pageInfo);
	
	List<Trade> selectTradeList(Trade trade);//查询当前大盘交易行情

	int addSaleInfo(Trade trade);

	int selectCount(Trade trade);
	
	public List<Trade> selectInfoByPrice(Double price);
	
	public int addBuyInfo(Trade trade);
	
	List<Trade> selectByExample(TradeExample example);
	
	int updateByPrimaryKey(Trade trade);
	
	int deleteByPrimaryKey(Long id);
	
	public int updateByPrimaryKeySelective(Trade trade);
	
	public List<Trade> selectList2(Trade trade, PageInfo pageInfo);
	
	public List<Trade> selectList3(Trade trade, PageInfo pageInfo);
	
	int selectCount2(Trade trade);
	
	public int selectCount3(Trade trade);
	
	public int selectCount4(Trade trade);
	
	public List<Trade> selectList4(Trade trade, PageInfo pageInfo);
	
}
