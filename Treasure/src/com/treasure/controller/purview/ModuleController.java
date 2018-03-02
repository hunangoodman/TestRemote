package com.treasure.controller.purview;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.treasure.bean.ResponseResult;
import com.treasure.constant.SysConstants;
import com.treasure.controller.common.BaseController;
import com.treasure.model.Function;
import com.treasure.model.Module;
import com.treasure.model.Role;
import com.treasure.model.RoleModule;
import com.treasure.service.FunctionService;
import com.treasure.service.ModuleService;
import com.treasure.service.RoleService;

/**
 * @project House-Intro-Web
 * @package com.xinhai.houseintro.web.purview
 * @class ModuleController.java
 * @author jiagui E-mail:1257896208@qq.com
 * @date 2015年10月25日 下午4:55:05
 * @description 资源管理模块
 */
@Controller(value = "ModuleController")
@RequestMapping(value = "/mgr/module")
public class ModuleController extends BaseController {
	@Autowired
	@Qualifier(value = "moduleServiceImpl")
	private ModuleService moduleService;
	@Autowired
	@Qualifier(value = "functionServiceImpl")
	private FunctionService functionService;
	@Autowired
	@Qualifier(value = "roleServiceImpl")
	private RoleService roleService;
	private static final Logger log = Logger.getLogger(ModuleController.class);

	// 进入页面
	@RequestMapping(value = "/modulelist")
	public String modulelist() {
		log.info("进入资源管理模块页面...");
		return "purview/module/modulelist";
	}

	@RequestMapping(value = "/moduleform")
	public String moduleform() {
		return "purview/module/moduleform";
	}

	/**
	 * 查询模块资源列表
	 * 
	 * @param module
	 * @param pageVo
	 * @return
	 */
	@RequestMapping(value = "/showList", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult showList() {
		log.info("查询module数据");
		ResponseResult result = new ResponseResult();
		try {
			List<Module> list = moduleService.selectAllList();// 查询符合条件的数据
			result.setCode(SysConstants.STATUS_TRUE);
			result.setList(list);
		} catch (Exception e) {// 异常处理
			log.info(e.getMessage());
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg(e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/showModuleList", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult showModuleList(Integer roleId) {
		log.info("查询module数据");
		ResponseResult result = new ResponseResult();
		try {
			List<Module> list = moduleService.selectModuleList(roleId);// 查询符合条件的数据
			result.setCode(SysConstants.STATUS_TRUE);
			result.setList(list);
		} catch (Exception e) {// 异常处理
			log.info(e.getMessage());
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg(e.getMessage());
		}
		return result;
	}

	/**
	 * 增加
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addModule", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult addModule(Module module) {
		int num = 0;
		ResponseResult result = new ResponseResult();
		try {
			if (module.getModuleId() == null) {
				num = moduleService.addModule(module);
				if (num > 0) {
					result.setCode(SysConstants.STATUS_TRUE);
					result.setMsg("添加成功~");
				} else {
					result.setCode(SysConstants.STATUS_FALSE);
					result.setMsg("添加失败！");
				}
			} else {
				num = moduleService.updateModule(module);
				if (num > 0) {
					result.setCode(SysConstants.STATUS_TRUE);
					result.setMsg("修改成功~");
				} else {
					result.setCode(SysConstants.STATUS_FALSE);
					result.setMsg("修改失败！");
				}
			}
		} catch (Exception e) {
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg(e.getMessage());
			result.setObject(module);
		}
		return result;
	}

	/**
	 * 资源修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showUpdate", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult showUpdate(
			@RequestParam(required = false, value = "key") Integer moduleId) {
		ResponseResult result = new ResponseResult();
		try {
			if (moduleId != null) {
				Module module = moduleService.selectByKey(moduleId);
				List<Function> functions = module.getFunctions();
				if (module != null && functions != null && functions.size() > 0) {
					String functionIds = "";
					String functionNames = "";
					for (Function f : functions) {
						functionIds += f.getFunctionId() + ",";
						functionNames += f.getFunctionName() + ",";
					}
					module.setFunctionIds(functionIds.substring(0,
							functionIds.length() - 1));
					module.setFunctionNames(functionNames.substring(0,
							functionNames.length() - 1));
				}
				result.setCode(SysConstants.STATUS_TRUE);
				result.setObject(module);
			}
		} catch (Exception e) {
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg(e.getMessage());
			result.setObject(null);
		}
		return result;
	}

	/**
	 * 批量删除
	 * 
	 * @param keys
	 * @return
	 */
	@RequestMapping(value = "/deleteModule", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult deleteModule(
			@RequestParam(required = false, value = "keys") String keys) {
		log.info("主键：" + keys);
		ResponseResult result = new ResponseResult();
		try {
			if (StringUtils.hasText(keys)) {
				List<String> keyes = new ArrayList<String>();
				String[] objs = keys.split(",");
				for (String k : objs) {
					keyes.add(k);
				}
				int num = moduleService.deleteModuleByKey(keyes);
				if (num > 0) {
					result.setCode(SysConstants.STATUS_TRUE);
					result.setMsg("成功刪除" + num + "条数据");
				} else {
					result.setCode(SysConstants.STATUS_FALSE);
					result.setMsg("删除失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg(e.getMessage());
		}
		return result;
	}

	// 进入角色分配资源页面
	@RequestMapping(value = "/moduleselect", produces = "application/json;charset=utf-8")
	public String moduleSelect(Model model,
			@RequestParam(required = false, value = "roleId") Long roleId) {
		try {
			Role role = roleService.selectByKey(roleId);
			List<Module> list = moduleService.selectAllList();// 查询所有资源
			List<RoleModule> rlist = moduleService
					.selectRoleModuleByRoleId(roleId);// 查询该角色已分配的资源
			List<Function> functions = null;// 资源功能数组
			List<Function> flist = null;// 资源功能数组
			Function function = null;
			for (Module module : list) {
				for (RoleModule rs : rlist) {
					if (module.getModuleId() == rs.getModuleId()) {
						module.setChecked(true);
						break;
					}
				}
				functions = module.getFunctions();
				if (module.getChecked() && functions != null
						&& functions.size() > 0) {
					flist = new ArrayList<Function>();
					for (int i = 0, a = functions.size(); i < a; i++) {
						function = new Function();
						BeanUtils.copyProperties(function, functions.get(i));
						for (RoleModule rs : rlist) {
							if (module.getModuleId() == rs.getModuleId()
									&& function.getFunctionId() == rs
											.getFunctionId()) {
								function.setChecked(true);
								break;
							}
						}
						flist.add(function);
					}
					module.setFunctions(flist);
				}
			}
			model.addAttribute("list", list);
			model.addAttribute("role", role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "purview/role/moduleselect";
	}
}