package com.treasure.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treasure.bean.PageInfo;
import com.treasure.dao.TransferRecordMapper;
import com.treasure.model.TransferRecord;
import com.treasure.service.TransferRecordService;

@Service
public class TransferRecordServiceImpl implements TransferRecordService{
	
	@Autowired
	TransferRecordMapper transferRecordMapper;
	
	public List<TransferRecord> findInPage(TransferRecord transferRecord, PageInfo pageInfo) {
		List<TransferRecord> transfertran = transferRecordMapper.selectInPage(transferRecord , pageInfo);
		
		return transfertran;
	}
	public List<TransferRecord> findInPage1(TransferRecord transferRecord, PageInfo pageInfo) {
		List<TransferRecord> transfertran = transferRecordMapper.selectInPage1(transferRecord , pageInfo);
		
		return transfertran;
	}
	
	public int findTotal(TransferRecord transferRecord) {
		return transferRecordMapper.selectTotalRecord(transferRecord);
	}
	public int findTotal1(TransferRecord transferRecord) {
		return transferRecordMapper.selectTotalRecord(transferRecord);
	}
	
}
