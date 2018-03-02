package com.treasure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.treasure.model.UserRole;
import com.treasure.model.UserRoleExample;

public interface UserRoleMapper {
	int countByExample(UserRoleExample example);

	int deleteByExample(UserRoleExample example);

	int deleteByPrimaryKey(Long userRoleId);

	int insert(UserRole record);

	int insertSelective(UserRole record);

	List<UserRole> selectByExample(UserRoleExample example);

	UserRole selectByPrimaryKey(Long userRoleId);

	int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

	int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);

	int updateByPrimaryKeySelective(UserRole record);

	int updateByPrimaryKey(UserRole record);

	int deleteByUserId(Long userId);

	int insertList(@Param("list") List<UserRole> list);

	int deleteUserRoleByKey(@Param("list") List<?> list);

	String selectRoleIdsByKey(@Param("nid") Long nid);

	String selectRoleNamesByKey(@Param("nid") Long nid);
}