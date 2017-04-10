package com.cmc.common.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class DataUtil {

	public static Integer getIntegerValue(Map<String, Object> params, String key, Integer defaultValue) {
		try {
			return ((Number) params.get(key)).intValue();
		} catch (Exception e) {
		}
		return defaultValue;
	}

	public static Long getLongValue(Map<String, Object> params, String key, Long defaultValue) {
		try {
			return ((Number) params.get(key)).longValue();
		} catch (Exception e) {
		}
		return defaultValue;
	}

	public static Long getLongValue(Map<String, Object> params, String key) {
		return getLongValue(params, key, null);
	}

	public static Long getLongValue(HttpServletRequest request, String key, Long defaultValue) {
		try {
			String p = request.getParameter(key);
			return Long.parseLong(p);
		} catch (Exception e) {
		}
		return defaultValue;
	}

	public static Integer getIntegerValue(HttpServletRequest request, String key, Integer defaultValue) {
		try {
			String p = request.getParameter(key);
			return IdHandler.idDecryptToInteger(p);
		} catch (Exception e) {
		}
		return defaultValue;
	}

	public static Long getLongValue(HttpServletRequest request, String key) {
		return getLongValue(request, key, null);
	}

	public static Integer getIntegerValue(HttpServletRequest request, String key) {
		return getIntegerValue(request, key, null);
	}

	public static String getStringValue(HttpServletRequest request, String key) {
		return getStringValue(request, key, null);
	}

	public static Integer getIntegerValue(String num) {
		if (StringUtils.isBlank(num)) {
			return null;
		}
		try {
			return Integer.parseInt(num);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * @Description 获取请求中的指定参数值,如不存在或者取得的值为空字符串时则返回默认的返回值.
	 * @param request
	 *            请求
	 * @param key
	 *            参数的key
	 * @param defaultValue
	 *            默认的返回值
	 * @return 指定的参数对就的值，如不存在或者为空时返回默认的返回值.
	 */
	public static String getStringValue(HttpServletRequest request, String key, String defaultValue) {
		if (null == request || StringUtils.isBlank(key)) {
			return defaultValue;
		}
		String value = request.getParameter(key.trim());
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		} else {
			return value.trim();
		}
	}

	public static String getStringValue2(HttpServletRequest request, String key) {
		if (null == request || StringUtils.isBlank(key)) {
			return "";
		}
		String value = request.getParameter(key.trim());
		if (StringUtils.isBlank(value)) {
			return "";
		} else if (value.length() == 36) {
			return value.trim();
		} else {
			return "" + IdHandler.idDecryptToInteger(value);
		}
	}
}
