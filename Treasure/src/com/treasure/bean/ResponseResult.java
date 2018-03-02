package com.treasure.bean;

import java.util.List;

/**
 * @project Fusion-Common
 * @package com.fusionzoom.common.utils
 * @class Result.java
 * @author jiagui E-mail:<zhujiagui@zkingsoft.com>
 * @date 2015年9月9日 下午5:53:05
 * @description 返回结果封装类
 */
public class ResponseResult {
	private String code;// 状态码，true:成功，false:失败，error：错误,info:日志
	private String msg;// 提示信息
	private List<?> list;
	private List<?> errorList;
	private Object object;
	private PageInfo pageInfo;

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public List<?> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<?> errorList) {
		this.errorList = errorList;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
