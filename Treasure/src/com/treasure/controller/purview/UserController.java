package com.treasure.controller.purview;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.treasure.service.ModuleService;
import com.treasure.service.UserService;
import com.treasure.utils.AesUtil;

/**
 * @project House-Intro-Web
 * @package com.xinhai.houseintro.web.purview
 * @class UserController.java
 * @author jiagui E-mail:1257896208@qq.com
 * @date 2015年10月25日 下午5:47:27
 * @description 后台用户管理模块
 */
@Controller(value = "UserController")
@RequestMapping(value = "/mgr/user")
public class UserController extends BaseController {
	@Autowired
	@Qualifier(value = "userServiceImpl")
	private UserService userService;
	@Autowired
	@Qualifier(value = "moduleServiceImpl")
	private ModuleService moduleService;
	private static final Logger log = Logger.getLogger(UserController.class);
	@Value("${app.version}")
	private String version;

	// 进入页面
	@RequestMapping(value = "/userlist")
	public String userList() {
		log.info("进入用户页面...");
		return "purview/user/userlist";
	}

	@RequestMapping(value = "/userform")
	public String userform() {
		return "purview/user/userform";
	}

	@RequestMapping(value = "/updatepassword")
	public String updatepassword() {
		return "purview/user/updatepassword";
	}

	@RequestMapping(value = "/tologin")
	public String tologin() {
		return "purview/user/tologin";
	}

	@RequestMapping(value = "/login")
	public String login(HttpSession session, Model model) {
		session.setAttribute("version", version);
		return "purview/user/login";
	}

	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/loginlog")
	public String loginlog() {
		return "purview/user/loginlog";
	}

	@RequestMapping(value = "/logdetail")
	public String logdetail(Long userId, Model model) {
		model.addAttribute("userId", userId);
		return "purview/user/logdetail";
	}

	@RequestMapping(value = "/showList", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult showList(User user, PageInfo pageVo) {
		log.info("查询User数据");
		ResponseResult result = new ResponseResult();
		try {
			if (pageVo == null) {
				pageVo = new PageInfo();
			}
			List<User> list = userService.selectUserList(user, pageVo);// 查询符合条件的数据
			int totalRecord = userService.selectTotalRecord(user);
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

	/**
	 * 增加
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult addUser(User user) {
		int num = 0;
		ResponseResult result = new ResponseResult();
		try {
			if (user.getUserId() == null) {
				user.setPassword(AesUtil.encrypt(user.getPassword(), SysConstants.AES_KEY));
				user.setAddTime(new Date());
				num = userService.addUser(user);
				if (num > 0) {
					result.setCode(SysConstants.STATUS_TRUE);
					result.setMsg("添加成功~");
				} else {
					result.setCode(SysConstants.STATUS_FALSE);
					result.setMsg("添加失败！");
				}
			} else {
				num = userService.updateUser(user);
				if (num > 0) {
					result.setCode(SysConstants.STATUS_TRUE);
					result.setMsg("修改成功~");
				} else {
					result.setCode(SysConstants.STATUS_FALSE);
					result.setMsg("修改失败！");
				}
			}
		} catch (DuplicateKeyException e) {
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("用户名或工号已存在！");
			result.setObject(user);
		} catch (Exception e) {
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("系统错误!");
			result.setObject(user);
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
	public @ResponseBody ResponseResult showUpdate(@RequestParam(required = false, value = "key") Long userId) {
		ResponseResult result = new ResponseResult();
		try {
			if (userId != null) {
				User user = userService.selectUserByKey(userId);
				List<Role> list = user.getRlist();
				if (list != null && list.size() > 0) {
					String[] roleIds = new String[list.size()];
					for (int i = 0, a = list.size(); i < a; i++) {
						roleIds[i] = list.get(i).getRoleId() + "";
					}
					user.setRoleIds(StringUtils.join(roleIds, ","));
				}
				result.setCode(SysConstants.STATUS_TRUE);
				result.setObject(user);
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
	@RequestMapping(value = "/deleteUser", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult deleteUser(HttpSession session, HttpServletRequest request, @RequestParam(required = false, value = "keys") String keys) {
		log.info("主键：" + keys);
		ResponseResult result = new ResponseResult();
		int num = 0;
		try {
			List<String> keyes = new ArrayList<String>();
			String[] objs = keys.split(",");
			for (String k : objs) {
				keyes.add(k);
			}
			num = userService.deleteUserByKey(keyes);
			if (num > 0) {
				result.setCode(SysConstants.STATUS_TRUE);
				result.setMsg("成功删除" + num + "条数据");
			} else {
				result.setCode(SysConstants.STATUS_FALSE);
				result.setMsg("删除失败！");
			}
		} catch (Exception e) {
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("系统错误!");
		}
		return result;

	}

	/**
	 * 锁定、解锁用户
	 * 
	 * @param response
	 * @param userId
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateUserLock", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult updateUserLock(HttpSession session, HttpServletRequest request, @RequestParam(required = false, value = "status") Integer status, @RequestParam(required = false, value = "key") Long userId) throws Exception {
		int num = 0;
		ResponseResult result = new ResponseResult();
		try {
			User user = new User();
			user.setUserId(userId);
			user.setStatus(status);
			num = userService.updateUserLock(user);
			if (num > 0) {
				result.setCode(SysConstants.STATUS_TRUE);
				result.setMsg("操作成功！");
			} else {
				result.setCode(SysConstants.STATUS_FALSE);
				result.setMsg("操作失败！");
			}
		} catch (Exception e) {
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("系统错误!");
		}
		return result;
	}

	/**
	 * 重置密码
	 * 
	 * @param response
	 * @param key
	 * @throws Exception
	 */
	@RequestMapping(value = "/resetPassword", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult resetPassword(HttpSession session, HttpServletRequest request, @RequestParam(required = false, value = "userId") Long userId) throws Exception {
		int num = 0;
		ResponseResult result = new ResponseResult();
		try {
			User user = new User();
			user.setUserId(userId);
			user.setPassword(AesUtil.encrypt("123456", SysConstants.AES_KEY));
			num = userService.updateUserLock(user);
			if (num > 0) {
				result.setCode(SysConstants.STATUS_TRUE);
				result.setMsg("重置密码成功！");
			} else {
				result.setCode(SysConstants.STATUS_FALSE);
				result.setMsg("重置密码失败！");
			}
		} catch (Exception e) {
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("系统错误!");
		}
		return result;
	}

	/**
	 * 重置密码
	 * 
	 * @param response
	 * @param key
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatePassword", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult updatePassword(User user) throws Exception {
		return userService.updatePwd(user);
	}

	/**
	 * 登录验证
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/dologin", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult dologin(HttpSession session, HttpServletRequest request, User user, Model model) throws Exception {
		ResponseResult result = new ResponseResult();
		try {
			if (user != null) {
				user = userService.doLogin(user);
				String msg = user.getMsg();
				if (SysConstants.STATUS_TRUE.equals(msg)) {
					result.setCode(SysConstants.STATUS_TRUE);
					// 将登录信息放入session
					session.setAttribute(SysConstants.LOGIN_KEY, user);
					// 根据用户角色信息获取角色资源信息
					Map<Integer, RoleModule> roleResMap = moduleService.selectRoleModuleByUser(user);
					session.setAttribute(SysConstants.ROLE_RESOURCE_KEY, roleResMap);
				} else {
					result.setCode(SysConstants.STATUS_FALSE);
					result.setMsg(msg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("系统错误!");
		}
		return result;
	}

	@RequestMapping(value = "/exit")
	public String exit(HttpSession session, HttpServletRequest request, Model model) {
		try {
			session.setAttribute(SysConstants.LOGIN_KEY, null);
			session.setAttribute(SysConstants.ROLE_RESOURCE_KEY, null);
		} catch (Exception e) {
		}
		return "redirect:login";
	}
}
