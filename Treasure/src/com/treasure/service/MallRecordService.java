package com.treasure.service;

import java.util.List;

import com.treasure.bean.PageInfo;
import com.treasure.model.MallRecord;

public interface MallRecordService {
	public void inser(MallRecord mallRecord);
	public List<MallRecord> selectMallRecord(String mallStatus);
	public List<MallRecord> selectMallRecord(MallRecord mallRecord, PageInfo pageInfo);
	public int findTotal(MallRecord mallrecord);
	public List<MallRecord> selectList(MallRecord mallRecord, PageInfo pageInfo);
	public int findTotal1(MallRecord mallrecord);
}
