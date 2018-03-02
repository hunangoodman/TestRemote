package com.treasure.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.treasure.bean.PageInfo;
import com.treasure.model.BtoolsConsult;
import com.treasure.model.TransferRecord;

public interface TransferRecordService {
	 
	/**
	 * 分页查询对象
	 * */
	public List<TransferRecord> findInPage(TransferRecord transferRecord,  PageInfo pageInfo);
	public List<TransferRecord> findInPage1(TransferRecord transferRecord,  PageInfo pageInfo);
	
	/**
	 * 统计记录数BtoolsConsult
	 * 
	 */
	public int  findTotal(TransferRecord transferRecord);
	public int  findTotal1(TransferRecord transferRecord);
}
