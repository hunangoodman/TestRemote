package com.treasure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.treasure.model.Module;
import com.treasure.model.ModuleExample;
import com.treasure.model.User;

public interface ModuleMapper {
	int countByExample(ModuleExample example);

	int deleteByExample(ModuleExample example);

	int deleteByPrimaryKey(Integer moduleId);

	int insert(Module record);

	int insertSelective(Module record);

	List<Module> selectByExample(ModuleExample example);

	Module selectByPrimaryKey(Integer moduleId);

	int updateByExampleSelective(@Param("record") Module record, @Param("example") ModuleExample example);

	int updateByExample(@Param("record") Module record, @Param("example") ModuleExample example);

	int updateByPrimaryKeySelective(Module record);

	int updateByPrimaryKey(Module record);

	List<Module> selectModuleByRoleid(List<?> ids);

	List<Module> selectModuleByUser(@Param("user") User user);

	int deleteModuleByKey(List<?> list);

	int updateChildren(Module record);

	List<Module> selectModuleList(@Param("roleId") Integer roleId);
}