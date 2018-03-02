package com.treasure.service;

import java.util.List;

import com.treasure.bean.PageInfo;
import com.treasure.model.MarketRecords;




public interface MarketRecordService {
	public void inser(MarketRecords mallRecord);
	public List<MarketRecords> selectMallRecord(String mallStatus);
	public List<MarketRecords> selectMallRecord(MarketRecords mallRecord, PageInfo pageInfo);
	public int findTotal(MarketRecords mallrecord);
	public List<MarketRecords> selectList(MarketRecords mallRecord, PageInfo pageInfo);
	public int MarketRecord(MarketRecords mallrecord);
}
