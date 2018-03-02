package com.treasure.model;

import java.util.List;

public class RoleModule{

	private Long roleModuleId;

    private Long roleId;

    private Integer moduleId;

    private Integer functionId;
 // 无映射属性
 	private Function function;
 	private List<Function> flist;
    public Long getRoleModuleId() {
        return roleModuleId;
    }

    public void setRoleModuleId(Long roleModuleId) {
        this.roleModuleId = roleModuleId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public List<Function> getFlist() {
		return flist;
	}

	public void setFlist(List<Function> flist) {
		this.flist = flist;
	}
}