package com.treasure.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.treasure.constant.SysConstants;
import com.treasure.dao.ModuleFunctionMapper;
import com.treasure.dao.ModuleMapper;
import com.treasure.dao.RoleModuleMapper;
import com.treasure.model.Function;
import com.treasure.model.Module;
import com.treasure.model.ModuleFunction;
import com.treasure.model.ModuleFunctionExample;
import com.treasure.model.RoleModule;
import com.treasure.model.RoleModuleExample;
import com.treasure.model.User;
import com.treasure.service.ModuleService;

@Service(value = "moduleServiceImpl")
public class ModuleServiceImpl implements ModuleService {
	@Autowired
	@Qualifier(value = "moduleMapper")
	private ModuleMapper moduleMapper;
	@Autowired
	@Qualifier(value = "roleModuleMapper")
	private RoleModuleMapper roleModuleMapper;
	@Autowired
	@Qualifier(value = "moduleFunctionMapper")
	private ModuleFunctionMapper moduleFunctionMapper;

	public List<Module> selectAllList() {
		return moduleMapper.selectByExample(null);
	}

	public int addModule(Module module) {
		int result = moduleMapper.insertSelective(module);
		if (StringUtils.hasText(module.getFunctionIds())) {
			String[] ids = module.getFunctionIds().split(",");
			List<ModuleFunction> list = new ArrayList<ModuleFunction>();
			ModuleFunction rf = null;
			Integer moduleId = module.getModuleId();
			for (String id : ids) {
				rf = new ModuleFunction();
				rf.setModuleId(moduleId);
				rf.setFunctionId(Integer.valueOf(id));
				list.add(rf);
			}
			result += moduleFunctionMapper.insertList(list);
		}
		return result;
	}

	public int updateModule(Module module) {
		int result = 0;
		if (module.getLevel() != null && module.getLevel() == 0) {// 修改其子节点的启用状态
			result = moduleMapper.updateByPrimaryKeySelective(module) + moduleMapper.updateChildren(module);
		} else {
			result = moduleMapper.updateByPrimaryKeySelective(module);
		}
		if (StringUtils.hasText(module.getFunctionIds())) {
			String[] ids = module.getFunctionIds().split(",");
			List<ModuleFunction> list = new ArrayList<ModuleFunction>();
			ModuleFunction rf = null;
			Integer moduleId = module.getModuleId();
			for (String id : ids) {
				rf = new ModuleFunction();
				rf.setModuleId(moduleId);
				rf.setFunctionId(Integer.valueOf(id));
				list.add(rf);
			}
			ModuleFunctionExample example = new ModuleFunctionExample();
			example.createCriteria().andModuleIdEqualTo(module.getModuleId());
			result += moduleFunctionMapper.deleteByExample(example);
			result += moduleFunctionMapper.insertList(list);
		}
		return result;
	}

	public Module selectByKey(Integer sourceId) {
		return moduleMapper.selectByPrimaryKey(sourceId);
	}

	public int deleteModuleByKey(List<?> list) {
		moduleFunctionMapper.deleteModuleFunctionByKey(list);
		return moduleMapper.deleteModuleByKey(list);
	}

	public List<Module> selectModuleByRoleid(List<?> ids) {
		return moduleMapper.selectModuleByRoleid(ids);
	}

	public List<RoleModule> selectRoleModuleByRoleId(Long roleId) {
		RoleModuleExample example = new RoleModuleExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		return roleModuleMapper.selectByExample(example);
	}

	public List<Module> selectModuleByUser(User user) {
		return moduleMapper.selectModuleByUser(user);
	}

	public Map<Integer, RoleModule> selectRoleModuleByUser(User user) {
		if (user.getUserName().equals(SysConstants.SUPERMAN)) {// 超级用户不查询
			return null;
		}
		Map<Integer, RoleModule> roleResMap = null;
		List<RoleModule> list = roleModuleMapper.selectRoleModuleByRole(user.getUserId());
		if (list != null) {
			roleResMap = new HashMap<Integer, RoleModule>();
			List<Function> flist = null;
			RoleModule crm = null;
			for (RoleModule rs : list) {
				if (roleResMap.containsKey(rs.getModuleId())) {
					crm = roleResMap.get(rs.getModuleId());
					flist = crm.getFlist();
					if (rs.getFunction() != null) {
						if (flist != null) {
							flist.add(rs.getFunction());
						} else {
							flist = new ArrayList<Function>();
							flist.add(rs.getFunction());
						}
					}
					crm.setFlist(flist);
				} else {
					crm = rs;
					flist = new ArrayList<Function>();
					flist.add(rs.getFunction());
					crm.setFlist(flist);
					roleResMap.put(rs.getModuleId(), crm);
				}
			}
		}
		return roleResMap;
	}

	@Override
	public List<Module> selectModuleList(Integer roleId) {
		return moduleMapper.selectModuleList(roleId);
	}
}
