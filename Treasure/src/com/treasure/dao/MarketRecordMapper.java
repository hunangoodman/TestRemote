package com.treasure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.treasure.bean.PageInfo;
import com.treasure.model.MarketRecords;

public interface MarketRecordMapper {
	public void mallInser(@Param("record")MarketRecords mallRecord);
	public List<MarketRecords> selectMallRecord(String mallStatus);
	public List<MarketRecords> selectRecordList(@Param("record")MarketRecords mallRecord,@Param("pageInfo")PageInfo pageInfo);
	public List<MarketRecords>  selectRecord(@Param("record")MarketRecords mallRecord, @Param("pageInfo")PageInfo pageInfo);
	public int selectTotalRecord(@Param("record")MarketRecords mallrecord);
	public int selectTotalRecord1(@Param("record")MarketRecords mallrecord);
}
