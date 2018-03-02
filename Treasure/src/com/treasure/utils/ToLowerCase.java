package com.treasure.utils;

/**
 * @project Fusion-Common
 * @package com.fusionzoom.common.utils
 * @class ToLowerCase.java
 * @author jiagui E-mail:<zhujiagui@zkingsoft.com>
 * @date 2015年9月9日 下午5:54:28
 * @description 将对象属性转为大写字母转为_加小写，利于排序查询
 */
public class ToLowerCase {
	public static String toLowerCase(String str) {
		if (str == null || str.equals("")) {
			return null;
		}
		String str1 = str.toLowerCase();
		String sum = "";
		for (int i = 0; i < str.length(); i++) {
			if ((str.substring(i, 1 + i)).equals(str1.substring(i, 1 + i))) {
				sum += str1.substring(i, 1 + i);
			} else {
				sum += "_" + str1.substring(i, 1 + i);
			}
		}
		return sum;
	}
}
