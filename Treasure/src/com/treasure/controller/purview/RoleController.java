package com.treasure.controller.purview;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.treasure.bean.PageInfo;
import com.treasure.bean.ResponseResult;
import com.treasure.constant.SysConstants;
import com.treasure.controller.common.BaseController;
import com.treasure.model.Role;
import com.treasure.model.RoleModule;
import com.treasure.model.User;
import com.treasure.service.RoleService;

/**
 * @project House-Intro-Web
 * @package com.xinhai.houseintro.web.purview
 * @class RoleController.java
 * @author jiagui E-mail:1257896208@qq.com
 * @date 2015年10月25日 下午5:40:01
 * @description 角色管理模块
 */
@Controller(value = "RoleController")
@RequestMapping(value = "/mgr/role")
public class RoleController extends BaseController {
	@Autowired
	@Qualifier(value = "roleServiceImpl")
	private RoleService roleService;
	private static final Logger log = Logger.getLogger(RoleController.class);

	// 进入页面
	@RequestMapping(value = "/rolelist")
	public String roleList() {
		log.info("进入角色模块页面...");
		return "purview/role/rolelist";
	}

	@RequestMapping(value = "/rolelist2")
	public String rolelist2(HttpSession session, Role role, PageInfo pageVo, Model model) {
		try {
			User user = (User) session.getAttribute(SysConstants.LOGIN_KEY);
			if (user != null) {
				if (pageVo == null) {
					pageVo = new PageInfo();
				}
				List<Role> list = roleService.selectList(role, pageVo);// 查询符合条件的数据
				int totalRecord = roleService.selectTotalRecord(role);
				pageVo.setTotalRecord(totalRecord);
				model.addAttribute("role", role);
				model.addAttribute("list", list);
				model.addAttribute("jsonlist", JSONArray.fromObject(list));
				model.addAttribute("pageVo", pageVo);
			}
		} catch (Exception e) {// 异常处理
			log.info(e.getMessage());
		}
		return "purview/role/rolelist2";
	}

	// 进入页面
	@RequestMapping(value = "/roleform")
	public String roleform() {
		log.info("进入角色模块页面...");
		return "purview/role/roleform";
	}

	@RequestMapping(value = "/showList", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult showList(HttpSession session, Role role, PageInfo pageVo) {
		log.info("查询role数据");
		ResponseResult result = new ResponseResult();
		try {
			if (pageVo == null) {
				pageVo = new PageInfo();
			}
			List<Role> list = roleService.selectList(role, pageVo);// 查询符合条件的数据
			int totalRecord = roleService.selectTotalRecord(role);
			pageVo.setTotalRecord(totalRecord);
			result.setCode(SysConstants.STATUS_TRUE);
			result.setList(list);
			result.setPageInfo(pageVo);
		} catch (Exception e) {// 异常处理
			log.info(e.getMessage());
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("系统错误!");
		}
		return result;
	}

	// 查询role数据
	@RequestMapping(value = "/showAllList", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult showAllList() throws Exception {
		log.info("查询role数据");
		ResponseResult result = new ResponseResult();
		try {
			List<Role> list = roleService.showAllList();// 查询所有
			result.setCode(SysConstants.STATUS_TRUE);
			result.setList(list);
		} catch (Exception e) {// 异常处理
			log.info(e.getMessage());
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("系统错误!");
		}
		return result;
	}

	/**
	 * 增加
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addRole", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult addRole(HttpSession session, Role role, HttpServletRequest request) {
		ResponseResult result = new ResponseResult();
		int num = 0;
		try {
			if (role.getRoleId() == null) {
				role.setAddTime(new Date());
				num = roleService.addRole(role);
				if (num > 0) {
					result.setCode(SysConstants.STATUS_TRUE);
					result.setMsg("添加成功!");
				} else {
					result.setCode(SysConstants.STATUS_FALSE);
					result.setMsg("添加失败!");
				}
			} else {
				num = roleService.updateRole(role);
				if (num > 0) {
					result.setCode(SysConstants.STATUS_TRUE);
					result.setMsg("修改成功!");
				} else {
					result.setCode(SysConstants.STATUS_FALSE);
					result.setMsg("修改失败!");
				}
			}
		} catch (DuplicateKeyException e) {
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("角色名称已存在!");
			result.setObject(role);
		} catch (Exception e) {
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("系统错误!");
			result.setObject(role);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveRoles", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult saveRoles(HttpSession session, String data, HttpServletRequest request) {
		ResponseResult result = new ResponseResult();
		log.info("data:" + data);
		try {
			List<Role> list = null;
			JSONArray array = null;// 将json数组转为对象
			array = JSONArray.fromObject(data);
			list = (List<Role>) JSONArray.toCollection(array, Role.class);
			int num = roleService.saveRoles(list);
			if (num > 0) {
				result.setCode(SysConstants.STATUS_TRUE);
				result.setMsg("保存成功!");
			} else {
				result.setCode(SysConstants.STATUS_FALSE);
				result.setMsg("保存失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("系统错误!");
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
	public @ResponseBody ResponseResult showUpdate(@RequestParam(required = false, value = "key") Long roleId) {
		ResponseResult result = new ResponseResult();
		try {
			if (roleId != null) {
				Role role = roleService.selectByKey(roleId);
				result.setCode(SysConstants.STATUS_TRUE);
				result.setObject(role);
			}
		} catch (Exception e) {
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("系统错误!");
			result.setObject(null);
		}
		return result;
	}

	/**
	 * 批量删除
	 * 
	 * @param response
	 * @param userId
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteRole", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult deleteRole(HttpSession session, HttpServletRequest request, @RequestParam(required = false, value = "keys") String keys) {
		log.info("主键：" + keys);
		int num = 0;
		ResponseResult result = new ResponseResult();
		try {
			List<String> keyes = new ArrayList<String>();
			String[] objs = keys.split(",");
			for (String k : objs) {
				keyes.add(k);
			}
			num = roleService.deleteRoleByKey(keyes);
			if (num > 0) {
				result.setCode(SysConstants.STATUS_TRUE);
				result.setMsg("成功删除" + num + "条数据");
			} else {
				result.setCode(SysConstants.STATUS_FALSE);
				result.setMsg("删除失败!");
			}
		} catch (Exception e) {
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("系统错误!");
		}
		return result;
	}

	/**
	 * 分配角色资源
	 * 
	 * @param response
	 * @param key
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addRoleResource", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult addRoleResource(@RequestParam(required = false, value = "datas") String datas) throws Exception {
		ResponseResult result = new ResponseResult();
		log.info("数据：" + datas);
		int num = 0;
		try {
			List<RoleModule> list = null;
			JSONArray array = JSONArray.fromObject(datas);
			list = (List<RoleModule>) JSONArray.toCollection(array, RoleModule.class);
			if (list != null) {
				num = roleService.saveRoleModule(list);
				if (num > 0) {
					result.setCode(SysConstants.STATUS_TRUE);
					result.setMsg("分配成功!");
				} else {
					result.setCode(SysConstants.STATUS_FALSE);
					result.setMsg("分配失败!");
				}
			}
		} catch (Exception e) {
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("系统错误!");
		}
		return result;
	}

	/**
	 * 遍历公司的角色 查询下面的用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectUserByRole/{companyId}", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult selectUserByRole(@PathVariable("companyId") Integer companyId) {
		ResponseResult result = new ResponseResult();
		try {
			List<Role> list = roleService.selectAllUserAndRole(companyId);
			result.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
