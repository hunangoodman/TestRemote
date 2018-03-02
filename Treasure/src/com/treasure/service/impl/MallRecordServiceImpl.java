package com.treasure.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treasure.bean.PageInfo;
import com.treasure.dao.MallRecordMapper;
import com.treasure.model.MallRecord;
import com.treasure.service.MallRecordService;
import com.treasure.utils.DateFormatUtils;
@Service
public class MallRecordServiceImpl implements MallRecordService{
	@Autowired
	MallRecordMapper mallRecordMapper;
	

	public List<MallRecord> selectMallRecord(String mallStatus) {
		List<MallRecord> m = mallRecordMapper.selectMallRecord(mallStatus);
		return m;
	}

	@Override
	public void inser(MallRecord mallRecord) {
		
		mallRecord.setMallCreatetime( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		mallRecordMapper.mallInser(mallRecord);
	}
	
	
	public int findTotal(MallRecord mallrecord) {
		
		return (mallRecordMapper).selectTotalRecord(mallrecord);
	}

	@Override
	public List<MallRecord> selectMallRecord(MallRecord mallRecord, PageInfo pageInfo) {
		
		return mallRecordMapper.selectRecord(mallRecord,pageInfo);
	}

	public List<MallRecord> selectList(MallRecord mallRecord, PageInfo pageInfo) {
		return mallRecordMapper.selectRecordList(mallRecord, pageInfo);
	}

	@Override
	public int findTotal1(MallRecord mallrecord) {
		
		return mallRecordMapper.selectTotalRecord1(mallrecord);
	}

}
