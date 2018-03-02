package com.treasure.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treasure.bean.PageInfo;
import com.treasure.dao.CoinRecordMapper;
import com.treasure.model.CoinRecord;
import com.treasure.model.Member;
import com.treasure.service.CoinRecordService;

@Service
public class CoinRecordServiceImpl implements CoinRecordService{
	@Autowired
	private CoinRecordMapper cmapper;
	@Override
	public List<CoinRecord> findAll(CoinRecord member,PageInfo pageVo) {
		return cmapper.findAll(member,pageVo);
	}
	@Override
	public List<CoinRecord> findByName(String name) {
		return cmapper.findByName(name);
	}
	@Override
	public int selectCount(CoinRecord member) {
		int count=cmapper.selectCount();
		return count;
	}
	@Override
	public void addCoinRecode(CoinRecord cord) {
		 cmapper.addRecoed(cord);
	}
}
