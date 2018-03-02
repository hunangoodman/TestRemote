package com.treasure.service;

import java.util.List;
import java.util.Map;

import com.treasure.model.Module;
import com.treasure.model.RoleModule;
import com.treasure.model.User;

public interface ModuleService {
	List<Module> selectAllList();

	int addModule(Module module);

	int updateModule(Module module);

	Module selectByKey(Integer sourceId);

	int deleteModuleByKey(List<?> list);

	List<Module> selectModuleByRoleid(List<?> ids);

	List<RoleModule> selectRoleModuleByRoleId(Long roleId);

	Map<Integer, RoleModule> selectRoleModuleByUser(User user);

	List<Module> selectModuleList(Integer roleId);
	
	List<Module> selectModuleByUser(User user) ;
}
