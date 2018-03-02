package com.treasure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.treasure.bean.PageInfo;
import com.treasure.model.MallRecord;

public interface MallRecordMapper {
	public void mallInser(@Param("record")MallRecord mallRecord);
	public List<MallRecord> selectMallRecord(String mallStatus);
	public List<MallRecord> selectRecordList(@Param("record")MallRecord mallRecord,@Param("pageInfo")PageInfo pageInfo);
	public List<MallRecord>  selectRecord(@Param("record")MallRecord mallRecord, @Param("pageInfo")PageInfo pageInfo);
	public int selectTotalRecord(@Param("record")MallRecord mallrecord);
	public int selectTotalRecord1(@Param("record")MallRecord mallrecord);

}
