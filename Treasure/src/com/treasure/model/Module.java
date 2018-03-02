package com.treasure.model;

import java.io.Serializable;
import java.util.List;

public class Module implements Serializable{
	private static final long serialVersionUID = -5577351599834473579L;

	private Integer moduleId;

    private String moduleName;

    private String path;

    private Integer level;

    private String icon;

    private Integer parentId;

    private Integer isUsed;

    private String description;

    private Integer openType;
	/**
	 * 无映射属性
	 */
	private Integer parentSourceLevel;
	private List<Function> functions;// 资源功能数组
	private List<Module> childList;// 子集
	private Boolean checked = false;// 是否选中
	private String functionIds;// 模块添加时选择的功能id
	private String functionNames;// 模块添加时选择的功能id
	private Long userId;
	private Module child;
	private Integer roleId;
    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOpenType() {
        return openType;
    }

    public void setOpenType(Integer openType) {
        this.openType = openType;
    }

	public Integer getParentSourceLevel() {
		return parentSourceLevel;
	}

	public void setParentSourceLevel(Integer parentSourceLevel) {
		this.parentSourceLevel = parentSourceLevel;
	}

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

	public List<Module> getChildList() {
		return childList;
	}

	public void setChildList(List<Module> childList) {
		this.childList = childList;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getFunctionIds() {
		return functionIds;
	}

	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}

	public String getFunctionNames() {
		return functionNames;
	}

	public void setFunctionNames(String functionNames) {
		this.functionNames = functionNames;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Module getChild() {
		return child;
	}

	public void setChild(Module child) {
		this.child = child;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}