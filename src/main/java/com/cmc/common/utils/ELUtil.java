package com.cmc.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ELUtil {

	// 手机号码
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^1[358]\\d{9}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	//中文，字母或者数字
	public static boolean isString(String str) {
		Pattern p = Pattern.compile("^([\u4E00-\u9FA5]|[a-zA-Z]|[0-9])+$");
		Matcher m = p.matcher(str);
		return m.matches();
	}

	// 匹配真实姓名(2~7个中文或者3~10个英文)只能是中文或英文，不能为数字或其他字符，汉字和字母不能同时出现
	public static boolean isName(String name) {
		Pattern p = Pattern.compile("^([\u4E00-\u9FA5]{2,7})|([a-zA-Z]{3,10})$");
		Matcher m = p.matcher(name);
		return m.matches();
	}

	// 身份证15位的和18位的，而且后面还可以跟(17位加)大写的X
	public static boolean isCard(String card) {
		Pattern p = Pattern.compile("^\\d{15}(\\d{2}[0-9xX])?$");
		Matcher m = p.matcher(card);
		return m.matches();
	}

	// 邮编
	public static boolean isPost(String post) {
		Pattern p = Pattern.compile("^[1-9]\\d{5}(?!\\d)$");
		Matcher m = p.matcher(post);
		return m.matches();
	}

	// 在Integer范围的数字
	public static boolean isNumber(String maths) {
		boolean boo = false;
		Pattern p = Pattern.compile("[0-9]*");
		Matcher m = p.matcher(maths);
		boo = m.matches();
		if (boo) {
			try {
				Integer.parseInt(maths);
			} catch (Exception e) {
				boo = false;
			}

		}
		return boo;
	}

	public static void main(String[] args) {

		System.out.println(isNumber(""));
	}
}
