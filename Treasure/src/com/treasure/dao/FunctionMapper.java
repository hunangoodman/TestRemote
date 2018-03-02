package com.treasure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.treasure.bean.PageInfo;
import com.treasure.model.Function;
import com.treasure.model.FunctionExample;

public interface FunctionMapper {
	int countByExample(FunctionExample example);

	int deleteByExample(FunctionExample example);

	int deleteByPrimaryKey(Integer functionId);

	int insert(Function record);

	int insertSelective(Function record);

	List<Function> selectByExample(FunctionExample example);

	Function selectByPrimaryKey(Integer functionId);

	int updateByExampleSelective(@Param("record") Function record, @Param("example") FunctionExample example);

	int updateByExample(@Param("record") Function record, @Param("example") FunctionExample example);

	int updateByPrimaryKeySelective(Function record);

	int updateByPrimaryKey(Function record);

	int deleteFunctionByKey(List<?> list);// 批量刪除

	int selectTotalRecord(@Param("function") Function function);

	List<Function> selectList(@Param("function") Function function, @Param("pageVo") PageInfo pageVo);
}