package com.treasure.dao;

import com.treasure.model.IncomeRecord;
import com.treasure.model.IncomeRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IncomeRecordMapper {
    int countByExample(IncomeRecordExample example);

    int deleteByExample(IncomeRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(IncomeRecord record);

    int insertSelective(IncomeRecord record);

    List<IncomeRecord> selectByExample(IncomeRecordExample example);

    List<IncomeRecord> selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") IncomeRecord record, @Param("example") IncomeRecordExample example);

    int updateByExample(@Param("record") IncomeRecord record, @Param("example") IncomeRecordExample example);

    int updateByPrimaryKeySelective(IncomeRecord record);

    int updateByPrimaryKey(IncomeRecord record);
    
    int insertIncome(IncomeRecord record);
}
