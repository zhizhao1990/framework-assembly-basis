package com.cmc.common.utils;

/**
 * @ClassName HtmlUtil
 * @Description html的工具类，字符实体转换等
 * @author linb
 * @date 2015年10月29日 下午7:38:13
 */
public class HtmlUtil {

	// html字符实体对
	private static String[][] charEntity = { { "nbsp", " " }, { "quot", "\"" }, { "apos", "\'" }, { "amp", "&" }, { "lt", "<" }, { "gt", ">" }, { "alpha", "α" }, { "beta", "β" }, { "gamma", "γ" }, { "ndash", "–" }, { "mdash", "—" }, { "ldquo", "“" }, { "rdquo", "”" } };

	public static void main(String[] args) {
		String str = TranslateCharToEntiry("&nbsp;");
		System.out.println(str);
	}

	/**
	 * @Description 将字符串中的字符转换成实体
	 * @param str 字符串
	 * @return 转换后的字符串
	 */
	public static String TranslateCharToEntiry(String str) {
		if (null == str) {
			return str;
		}
		for (int i = 0; i < charEntity.length; i++) {
			str = str.replaceAll("&" + charEntity[i][0] + ";", charEntity[i][1]);
		}
		return str;
	}
}
