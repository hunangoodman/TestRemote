package com.treasure.constant;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.treasure.bean.TokenBean;

public class StaticConstants {
	public static Map<String, TokenBean> tokenMap = new HashMap<String, TokenBean>();
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
}
