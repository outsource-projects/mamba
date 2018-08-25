package com.renguangli.mamba.util;

/**
 * @author renguangli
 * @since jdk1.8
 */
public class StrUtils {
	
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str.trim());
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
}
