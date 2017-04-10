package com.cmc.common.utils;

import java.math.BigDecimal;

import com.cmc.common.constants.Constants;

//import net.sourceforge.pinyin4j.PinyinHelper;

public class StringUtil {

	public static String getImg(String path) {
		String imgUrl = null;
		if (path != null && !path.equals("")) {
			imgUrl = Constants.PHOTO_URL_PREFIX + path;
		}
		return imgUrl;
	}

	public static String getPhoneNumber(String phone) {
		String phoneNumber = phone.substring(0, 3) + "****"
				+ phone.substring(7);
		return phoneNumber;
	}

	public static String plus(String p1, String p2) {
		Integer sum = getInt(p1, 0) + getInt(p2, 0);
		return sum.toString();
	}

	public static Integer getInt(String i, Integer dv) {
		try {
			return Integer.parseInt(i);
		} catch (NumberFormatException e) {
		}
		return dv;
	}

	/**
	 * 截取指定字节长度的字符串，不能返回半个汉字 例如： 如果网页最多能显示17个汉字，那么 length 则为 34
	 * StringTool.getSubString(str, 34);
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static String getSubString(String str, int length) {
		int count = 0;
		int offset = 0;
		char[] c = str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] > 256) {
				offset = 2;
				count += 2;
			} else {
				offset = 1;
				count++;
			}
			if (count == length) {
				return str.substring(0, i + 1);
			}
			if ((count == length + 1 && offset == 2)) {
				return str.substring(0, i);
			}
		}
		return "";
	}

	// 后台管理
//	public static String StringToLetter(String args) {
//		String convert = "";
//		for (int j = 0; j < args.length(); j++) {
//			char word = args.charAt(j);
//			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
//			if (pinyinArray != null) {
//				convert += pinyinArray[0].charAt(0);
//			} else {
//				convert += word;
//			}
//		}
//		return convert.toUpperCase();
//	}

	public static boolean isContans(String list, String id) {
		return list.contains(id);
	}
	//富文本装换为String
	public static String htmlToString(String html){
		return html.replaceAll("<[^>]+>", "");
	}
	
	//富文本装换为String
	public static String fomortNull(String args){
		if(args == null || "null".equals(args)){
			return "";
		}else{
		return args;
		}
	}
	
	public static String moneyDBformat(String money){
		BigDecimal b1 = new BigDecimal(money);
		BigDecimal b2 = new BigDecimal("100");
		return b1.multiply(b2).toString();
	}
	
	public static String formatDBmoney(String money){
		BigDecimal b1 = new BigDecimal(money);
		BigDecimal b2 = new BigDecimal("100");
		return b1.divide(b2).toString();
	}
}
