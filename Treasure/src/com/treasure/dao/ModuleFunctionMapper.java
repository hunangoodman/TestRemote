package com.treasure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.treasure.model.ModuleFunction;
import com.treasure.model.ModuleFunctionExample;

public interface ModuleFunctionMapper {
	int countByExample(ModuleFunctionExample example);

	int deleteByExample(ModuleFunctionExample example);

	int deleteByPrimaryKey(Integer moduleFunctionId);

	int insert(ModuleFunction record);

	int insertSelective(ModuleFunction record);

	List<ModuleFunction> selectByExample(ModuleFunctionExample example);

	ModuleFunction selectByPrimaryKey(Integer moduleFunctionId);

	int updateByExampleSelective(@Param("record") ModuleFunction record, @Param("example") ModuleFunctionExample example);

	int updateByExample(@Param("record") ModuleFunction record, @Param("example") ModuleFunctionExample example);

	int updateByPrimaryKeySelective(ModuleFunction record);

	int updateByPrimaryKey(ModuleFunction record);

	int insertList(@Param("list") List<ModuleFunction> list);

	int deleteModuleFunctionByKey(@Param("list") List<?> list);
}