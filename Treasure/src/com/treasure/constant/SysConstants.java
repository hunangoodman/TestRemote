package com.treasure.constant;


/**
 * @project Treasure
 * @package com.treasure.constant
 * @class SysConstants.java
 * @author jiagui E-mail:1257896208@qq.com
 * @date 2017年9月29日 上午12:20:16
 * @description 系统常量
 */
public interface SysConstants {
	String AES_KEY = "Treasure2017Glod";// MD5加密密钥
	String loginInfoKey = "loginInfo";
	// 超级管理员
	String SUPERMAN = "admin";
	// 登录会话key值
	String LOGIN_KEY = "loginInfo";// 后台登录用户信息
	String ROLE_RESOURCE_KEY = "roleResourceList";
	/**
	 * 状态码
	 */
	String STATUS_TRUE = "true";// 成功
	String STATUS_FALSE = "false";// 失败
	String STATUS_ERROR = "error";// 错误
	String STATUS_INFO = "info";// 日志
	String STATUS_SYSTEM_ERROR = "system error";// 系统错误
}
