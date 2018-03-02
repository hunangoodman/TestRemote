package com.treasure.dao;

import com.treasure.model.PriceRecord;
import com.treasure.model.PriceRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PriceRecordMapper {
	int countByExample(PriceRecordExample example);

	int deleteByExample(PriceRecordExample example);

	int deleteByPrimaryKey(Long id);

	int insert(PriceRecord record);

	int insertSelective(PriceRecord record);

	List<PriceRecord> selectByExample(PriceRecordExample example);

	PriceRecord selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") PriceRecord record, @Param("example") PriceRecordExample example);

	int updateByExample(@Param("record") PriceRecord record, @Param("example") PriceRecordExample example);

	int updateByPrimaryKeySelective(PriceRecord record);

	int updateByPrimaryKey(PriceRecord record);

	List<PriceRecord> selectPrices(@Param("record") PriceRecord price);
}