package com.treasure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.treasure.bean.PageInfo;
import com.treasure.model.BtoolsConsult;
import com.treasure.model.TransferRecord;

public interface TransferRecordMapper {
	
	public List<TransferRecord> selectInPage(@Param("record") TransferRecord transferRecord, @Param("pageInfo") PageInfo pageInfo);
	public List<TransferRecord> selectInPage1(@Param("record") TransferRecord transferRecord, @Param("pageInfo") PageInfo pageInfo);
	
	public int  selectTotalRecord(@Param("record") TransferRecord transferRecord);
	public int  selectTotalRecord1(@Param("record") TransferRecord transferRecord);
}
