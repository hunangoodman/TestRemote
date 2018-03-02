package com.treasure.bean;

public class Result {
	private Integer code = 0;// 处理状态码，1:成功，0:失败
	private String errorCode;// 错误状态码
	private String msg;// 提示信息

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return 1 == this.code;
	}
}
