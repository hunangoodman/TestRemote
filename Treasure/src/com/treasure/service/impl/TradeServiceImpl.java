package com.treasure.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treasure.bean.PageInfo;
import com.treasure.dao.MemberMapper;
import com.treasure.dao.TradeMapper;
import com.treasure.model.Trade;
import com.treasure.model.TradeExample;
import com.treasure.service.TradeService;

@Service(value = "tradeServiceImpl")
public class TradeServiceImpl implements TradeService {

	@Autowired
	private TradeMapper tradeMapper;
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public List<Trade> queryTradeInfo(String memberId, PageInfo pageInfo) {
		return tradeMapper.selectTradeByPageWithMemberId(memberId, pageInfo);
	}

	@Override
	public int addSendInfo(Trade trade) {
		//return tradeMapper.insertSelective(trade) + memberMapper.updateByTrade1(trade) + memberMapper.updateByTrade2(trade);
		return tradeMapper.insertSelective(trade);
	}

	@Override
	public List<Trade> selectList(Trade trade, PageInfo pageInfo) {
		return tradeMapper.selectList(trade, pageInfo);
	}
	
	@Override
	public List<Trade> selectList2(Trade trade, PageInfo pageInfo) {
		return tradeMapper.selectList2(trade, pageInfo);
	}
	
	@Override
	public List<Trade> selectList3(Trade trade, PageInfo pageInfo) {
		return tradeMapper.selectList3(trade, pageInfo);
	}
	
	@Override
	public List<Trade> selectList4(Trade trade, PageInfo pageInfo) {
		return tradeMapper.selectList4(trade, pageInfo);
	}
	
	@Override
	public int selectCount2(Trade trade) {
		return tradeMapper.selectCount2(trade);
	}
	
	@Override
	public int selectCount3(Trade trade) {
		return tradeMapper.selectCount3(trade);
	}
	
	@Override
	public int selectCount4(Trade trade) {
		return tradeMapper.selectCount4(trade);
	}

	@Override
	public List<Trade> selectTradeList(Trade trade) {
		return tradeMapper.selectTradeList(trade);
	}

	@Override
	public int addSaleInfo(Trade trade) {
		return tradeMapper.insertSelective(trade) /*+ memberMapper.updateByTrade2(trade)*/;
	}
	
	@Override
	public int addBuyInfo(Trade trade) {
		return tradeMapper.insertSelective(trade);
	}
	
	@Override
	public int selectCount(Trade trade) {
		return tradeMapper.selectCount(trade);
	}
	@Override
	public List<Trade> selectInfoByPrice(Double price) {
		return tradeMapper.selectInfoByPrice(price);
	}

	@Override
	public List<Trade> selectByExample(TradeExample example) {
		return tradeMapper.selectByExample(example);
	}

	@Override
	public int updateByPrimaryKey(Trade trade) {
		return tradeMapper.updateByPrimaryKey(trade);
	}
	@Override
	public int updateByPrimaryKeySelective(Trade trade) {
		return tradeMapper.updateByPrimaryKeySelective(trade);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return tradeMapper.deleteByPrimaryKey(id);
	}
	
}
