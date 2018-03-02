package com.treasure.exception;

/**
 * @author JIAGUI
 * @email 1257896208@qq.com
 * @date 2017年3月24日 上午10:17:24
 * @description 自定义异常
 */
public class TreasureException extends Exception {
	private static final long serialVersionUID = 5719597552022873366L;

	public TreasureException() {
		super();
	}

	public TreasureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super();
	}

	public TreasureException(String message, Throwable cause) {
		super(message, cause);
	}

	public TreasureException(String message) {
		super(message);
	}

	public TreasureException(Throwable cause) {
		super(cause);
	}
}
