package com.treasure.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.treasure.bean.PageInfo;
import com.treasure.dao.RoleMapper;
import com.treasure.dao.RoleModuleMapper;
import com.treasure.model.Role;
import com.treasure.model.RoleModule;
import com.treasure.service.RoleService;

@Service(value = "roleServiceImpl")
public class RoleServiceImpl implements RoleService {
	@Autowired
	@Qualifier(value = "roleMapper")
	private RoleMapper roleMapper;
	@Autowired
	@Qualifier(value = "roleModuleMapper")
	private RoleModuleMapper roleModuleMapper;

	public int selectTotalRecord(Role role) {
		return roleMapper.selectTotalRecord(role);
	}

	public List<Role> selectList(Role role, PageInfo pageVo) {
		return roleMapper.selectList(role, pageVo);
	}

	public int addRole(Role role) {
		return roleMapper.insertSelective(role);
	}

	public int updateRole(Role role) {
		return roleMapper.updateByPrimaryKeySelective(role);
	}

	public Role selectByKey(Long roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}

	public int deleteRoleByKey(List<?> list) {
		roleModuleMapper.deleteRoleModuleByKey(list);
		return roleMapper.deleteRoleByKey(list);
	}

	public List<Role> showAllList() {
		return roleMapper.selectByExample(null);
	}

	public int saveRoleModule(List<RoleModule> list) {
		if (list != null && list.size() > 0) {
			int result = roleModuleMapper.deleteRoleModuleByRole(list.get(0).getRoleId());
			result += roleModuleMapper.saveRoleModule(list);
			return result;
		}
		return 0;
	}

	public List<Role> selectAllUserAndRole(Integer companyId) {
		return roleMapper.selectAllUserAndRole(companyId);
	}

	@Override
	public int saveRoles(List<Role> list) {
		int num = 0;
		if (list != null && list.size() > 0) {
			for (Role role : list) {
				num += roleMapper.updateByPrimaryKeySelective(role);
			}
		}
		return num;
	}

	@Override
	public List<Role> selectAll() {
		return roleMapper.selectByExample(null);
	}
}
