package com.treasure.dao;

import com.treasure.bean.PageInfo;
import com.treasure.model.Trade;
import com.treasure.model.TradeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TradeMapper {
	int countByExample(TradeExample example);

	int deleteByExample(TradeExample example);

	int deleteByPrimaryKey(Long id);

	int insert(Trade record);

	int insertSelective(Trade record);

	List<Trade> selectByExample(TradeExample example);

	Trade selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") Trade record, @Param("example") TradeExample example);

	int updateByExample(@Param("record") Trade record, @Param("example") TradeExample example);

	int updateByPrimaryKeySelective(Trade record);

	int updateByPrimaryKey(Trade record);

	List<Trade> selectTradeByPageWithMemberId(@Param("memberId") String memberId, @Param("pageVo") PageInfo pageInfo);

	List<Trade> selectList(@Param("trade") Trade trade, @Param("pageVo") PageInfo pageInfo);

	List<Trade> selectTradeList(@Param("trade") Trade trade);

	List<Trade> selectByPrice(@Param("price") Double price);

	int selectCount(@Param("trade") Trade trade);
	
	List<Trade> selectInfoByPrice(@Param("price") Double price);
	
	List<Trade> selectList2(@Param("trade") Trade trade, @Param("pageVo") PageInfo pageInfo);
	
	List<Trade> selectList3(@Param("trade") Trade trade, @Param("pageVo") PageInfo pageInfo);
	
	List<Trade> selectList4(@Param("trade") Trade trade, @Param("pageVo") PageInfo pageInfo);
	
	int selectCount2(@Param("trade") Trade trade);
	
	int selectCount3(@Param("trade") Trade trade);
	
	int selectCount4(@Param("trade") Trade trade);
	

}