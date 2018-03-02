package com.treasure.service;

import java.util.List;

import com.treasure.bean.PageInfo;
import com.treasure.model.Role;
import com.treasure.model.RoleModule;

public interface RoleService {
	int selectTotalRecord(Role role);

	List<Role> selectList(Role role, PageInfo pageVo);

	int addRole(Role role);

	int updateRole(Role role);

	int saveRoles(List<Role> list);

	Role selectByKey(Long roleId);

	int deleteRoleByKey(List<?> list);

	List<Role> showAllList();

	int saveRoleModule(List<RoleModule> list);

	List<Role> selectAllUserAndRole(Integer companyId);

	List<Role> selectAll();
}
