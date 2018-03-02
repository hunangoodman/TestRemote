package com.treasure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.treasure.bean.PageInfo;
import com.treasure.model.CoinRecord;
import com.treasure.model.Member;

public interface CoinRecordMapper {
	public void addRecoed(CoinRecord recode);
	
	public List<CoinRecord> findAll(@Param("member")CoinRecord member, @Param("pageVo")PageInfo pageVo);
	
	public List<CoinRecord> findByName(String name);
	
	public int selectCount();
}
