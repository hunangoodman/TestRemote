package com.treasure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.treasure.model.RoleModule;
import com.treasure.model.RoleModuleExample;

public interface RoleModuleMapper {
	int countByExample(RoleModuleExample example);

	int deleteByExample(RoleModuleExample example);

	int deleteByPrimaryKey(Long roleModuleId);

	int insert(RoleModule record);

	int insertSelective(RoleModule record);

	List<RoleModule> selectByExample(RoleModuleExample example);

	RoleModule selectByPrimaryKey(Long roleModuleId);

	int updateByExampleSelective(@Param("record") RoleModule record, @Param("example") RoleModuleExample example);

	int updateByExample(@Param("record") RoleModule record, @Param("example") RoleModuleExample example);

	int updateByPrimaryKeySelective(RoleModule record);

	int updateByPrimaryKey(RoleModule record);

	List<RoleModule> selectRoleModuleByRole(@Param("nid") Long nid);

	int deleteRoleModuleByRole(@Param("roleId") Long roleId);

	int saveRoleModule(@Param("list") List<RoleModule> list);

	int deleteRoleModuleByKey(@Param("list") List<?> list);
}