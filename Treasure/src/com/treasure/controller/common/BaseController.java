package com.treasure.controller.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.treasure.constant.SysConstants;
import com.treasure.exception.TreasureException;
import com.treasure.model.User;
import com.treasure.service.BaseService;

/**
 * @project House-Intro-Web
 * @package com.xinhai.houseintro.web.common
 * @class BaseController.java
 * @author jiagui E-mail:1257896208@qq.com
 * @date 2015年10月25日 下午4:34:05
 * @description Controller基类
 */
public abstract class BaseController {
	@Autowired
	@Qualifier(value = "baseServiceImpl")
	protected BaseService baseService;

	public String gotIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取用户Session
	 * 
	 * @return
	 */
	public User getSessionUser() {
		User user = null;
		Object obj = readSession(SysConstants.LOGIN_KEY);
		if (obj != null) {
			try {
				user = (User) obj;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	/**
	 * 读取Session
	 * 
	 * @param key
	 * @return
	 */
	public Object readSession(String key) {
		Object obj = null;
		try {
			HttpSession session = getRequest().getSession();
			obj = session.getAttribute(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * 负责对象类型转换
	 * 
	 * @param binder
	 * @throws Exception
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws TypeMismatchException {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm"), true));
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
		binder.registerCustomEditor(double.class, new DoubleEditor());
		binder.registerCustomEditor(int.class, new IntegerEditor());
	}

	public static class DoubleEditor extends PropertiesEditor {
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			if (StringUtils.isBlank(text)) {
				text = "0";
			}
			setValue(Double.parseDouble(text));
		}

		@Override
		public String getAsText() {
			return getValue().toString();
		}

	}

	public static class IntegerEditor extends PropertiesEditor {
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			if (StringUtils.isBlank(text)) {
				text = "0";
			}
			setValue(Integer.parseInt(text));
		}

		@Override
		public String getAsText() {
			return getValue().toString();
		}
	}

	@ExceptionHandler
	public String exp(HttpServletRequest request, Exception ex) {
		request.setAttribute("ex", ex);
		System.out.println("异常类型：" + ex.getClass().getName());
		// 根据不同错误转向不同页面
		if (ex instanceof TreasureException) {
			return "error-business";
		} else {
			return "error";
		}
	}
}
