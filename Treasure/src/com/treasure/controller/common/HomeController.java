package com.treasure.controller.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.treasure.constant.SysConstants;
import com.treasure.model.Module;
import com.treasure.model.User;
import com.treasure.service.ModuleService;

@Controller
@RequestMapping(value = "/")
public class HomeController extends BaseController {
	private static final Logger log = Logger.getLogger(HomeController.class);
	@Autowired
	@Qualifier(value = "moduleServiceImpl")
	private ModuleService moduleService;
	@Value("${app.version}")
	private String version;
	@RequestMapping(value = "/nodata")
	public String nodata() {
		return "nodata";
	}
	/**
	 * 主页根据用户加载可访问的模块资源
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mgr/home")
	public ModelAndView home(HttpSession session, Model model) {
		try {
			session.setAttribute("version", version);
			User user = this.getSessionUser();
			List<Module> list = null;
			if (user == null) {
				return null;
			}
			if (SysConstants.SUPERMAN.equals(user.getUserName())) {
				list = moduleService.selectModuleByUser(null);
			} else {
				list = moduleService.selectModuleByUser(user);
			}
			List<Module> dataList = toTreeData(list);
			log.info(dataList.size());
			model.addAttribute("dataList", dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/mgr/")
	public ModelAndView home1(HttpSession session, Model model) {
		try {
			session.setAttribute("version", version);
			User user = this.getSessionUser();
			List<Module> list = null;
			if (user == null) {
				return null;
			}
			if (SysConstants.SUPERMAN.equals(user.getUserName())) {
				list = moduleService.selectModuleByUser(null);
			} else {
				list = moduleService.selectModuleByUser(user);
			}
			List<Module> dataList = toTreeData(list);
			log.info(dataList.size());
			model.addAttribute("dataList", dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("home");
	}

	private List<Module> toTreeData(List<Module> list) {
		List<Module> dataList = new ArrayList<Module>();
		Map<Integer, List<Module>> map = new HashMap<Integer, List<Module>>();
		List<Module> childList = null;
		if (list != null && list.size() > 0) {
			for (Module r : list) {
				if (r.getLevel() == 0) {// 一级菜单
					dataList.add(r);
				}
				if (r.getParentId() != null) {
					childList = map.get(r.getParentId());
					if (childList == null) {
						childList = new ArrayList<Module>();
					}
					childList.add(r);
					map.put(r.getParentId(), childList);
				}
			}
			return getChildList(dataList, map);
		}
		return null;
	}

	private List<Module> getChildList(List<Module> dataList, Map<Integer, List<Module>> map) {
		List<Module> childList = null;
		for (Module m : dataList) {
			childList = map.get(m.getModuleId());
			m.setChildList(childList);
			if (childList != null && childList.size() > 0) {
				getChildList(childList, map);
			}
		}
		return dataList;
	}

	@RequestMapping(value = "/error")
	public String error() throws Exception {
		return "error";
	}

	@RequestMapping(value = "/permissionDenied")
	public String permissionDenied() throws Exception {
		return "purview/permissionDenied";
	}

	@RequestMapping(value = "/welcome")
	public String welcome() throws Exception {
		return "welcome";
	}
}
