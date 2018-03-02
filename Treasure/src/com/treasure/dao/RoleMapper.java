package com.treasure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.treasure.bean.PageInfo;
import com.treasure.model.Role;
import com.treasure.model.RoleExample;

public interface RoleMapper {
	int countByExample(RoleExample example);

	int deleteByExample(RoleExample example);

	int deleteByPrimaryKey(Long roleId);

	int insert(Role record);

	int insertSelective(Role record);

	List<Role> selectByExample(RoleExample example);

	Role selectByPrimaryKey(Long roleId);

	int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

	int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);

	// 分页查询
	int selectTotalRecord(@Param("role") Role role) ;

	List<Role> selectList(@Param("role") Role role, @Param("pageVo") PageInfo pageVo) ;

	List<Role> selectAllUserAndRole(@Param("companyId") Integer companyId) ;

	// 批量删除
	int deleteRoleByKey(List<?> list) ;

}