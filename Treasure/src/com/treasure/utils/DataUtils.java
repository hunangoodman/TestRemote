package com.treasure.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class DataUtils {
	public static Double getDouble(String value) {
		try {
			return Double.valueOf(value);
		} catch (Exception e) {
		}
		return null;
	}

	public static Integer getInteger(String value) {
		try {
			return Integer.valueOf(value);
		} catch (Exception e) {
		}
		return null;
	}

	public static Long getLong(String value) {
		try {
			return Long.valueOf(value);
		} catch (Exception e) {
		}
		return null;
	}

	public static Boolean getBoolean(String value) {
		try {
			return Boolean.valueOf(value);
		} catch (Exception e) {
		}
		return null;
	}
	public static Double mathRound(Double d, int n) {
		BigDecimal b = new BigDecimal(d);
		return b.setScale(n, BigDecimal.ROUND_FLOOR).doubleValue();
	}
	public static void main(String[] args) {
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		try {
			System.out.println(format.parse("2017-08-22").getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
