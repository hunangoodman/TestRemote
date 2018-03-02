package com.treasure.utils;

import java.util.UUID;

import com.treasure.constant.SysConstants;

/**
 * 
 * @author licong 2015 07 10
 * @version 1.0
 */
public class UUIDUtil {

	/**
	 * 生成36位UUID带-
	 * 
	 * @author licong
	 * @return 生成的UUID
	 */
	public static String generateUniqueID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 生成32位UUID 不带-
	 * 
	 * @author licong
	 * @return 生成的UUID
	 */
	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}

	/**
	 * 生成UUID false 不带- true 带-
	 * 
	 * @param split
	 * @author licong
	 * @return 生成的UUID
	 */
	public static String getUUID(boolean split) {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		// 去掉"-"符号
		if (!split) {
			return str.replaceAll("-", "");
		} else {
			return str;
		}
	}

	// 获得指定数量的UUID
	public static String[] getUUID(int number, boolean split) {
		if (number < 1) {
			return null;
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; i++) {
			ss[i] = getUUID(split);
		}
		return ss;
	}

	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
			"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
			"t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z" };

	public static String generateShortUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();

	}

	public static void main(String[] args) throws Exception {
		String[] ss = getUUID(10, false);
		for (int i = 0; i < ss.length; i++) {
			System.out.println("ss[" + i + "]->" + ss[i]);
		}
		System.out.println(AesUtil.encrypt(getUUID(false), SysConstants.AES_KEY));
	}
}
