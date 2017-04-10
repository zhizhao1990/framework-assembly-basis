/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2010 All Rights Reserved.
 */
package com.cmc.common.utils;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author John Lee
 * @version $Id: SysUtil.java,v 1.11 2012/01/10 12:10:21 chenc Exp $
 */
public class SysUtil {

	private static final Logger LOG = LoggerFactory.getLogger(SysUtil.class);

	private static final SimpleDateFormat FORMAT = new SimpleDateFormat(
			"yyyyMMdd00");
	private static final SimpleDateFormat FORMAT_VIEW = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat(
			"yyyyMMddHHmm");

	private static final Pattern PHONE_PATTERN = Pattern
			.compile("1\\d{10}");
	private static final Pattern REAL_NAME_PATTERN=Pattern.compile("(([\u4E00-\u9FA5]+|([a-zA-Z]+\\s?)+))");

	/**
	 * 
	 * 
	 * @param str
	 * @return
	 */
	public static boolean sqlInject(String str) {
		if (str == null || str.trim().equals("")) {
			return false;
		}

		String injectStr = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";

		String injectStrArray[] = injectStr.split("\\|");
		for (int i = 0; i < injectStrArray.length; i++) {
			if (str.indexOf(injectStrArray[i]) >= 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取一个随机字符串。
	 * 
	 * @param length
	 *            随机字符串的长度
	 * @param model
	 *            0 数字 1 字母 2 数字加字母
	 * 
	 * @return 一个随机字符串
	 */
	public static String getRandomString(int length, int model) {
		if (length < 1) {
			return "";
		}

		if (model < 0 || model > 2) {
			model = 0;
		}

		StringBuffer sb = new StringBuffer(length);

		for (int i = 0; i < length; i++) {
			if (model == 0) {
				sb.append(genRandomDigit(i != 0));
			} else if (model == 1) {
				sb.append(genRandomLetter());
			} else if (model == 2) {
				sb.append(genRandomChar(i != 0));
			}

		}

		return sb.toString();
	}

	/**
	 * 获取一个随机字符（允许数字0-9和小写字母）。
	 * 
	 * @return 一个随机字符
	 */
	private static char genRandomChar(boolean allowZero) {
		int randomNumber = 0;
		do {
			Random r = new Random();
			randomNumber = r.nextInt(36);
		} while ((randomNumber == 0) && !allowZero);

		if (randomNumber < 10) {
			return (char) (randomNumber + '0');
		} else {
			return (char) (randomNumber - 10 + 'a');
		}
	}

	private static char genRandomLetter() {
		int randomNumber = 0;
		Random r = new Random();
		randomNumber = r.nextInt(26);
		return (char) (randomNumber + 'a');
	}

	/**
	 * 获取一个随机字符（只允许数字0-9）。
	 * 
	 * @return 一个随机字符
	 */
	private static char genRandomDigit(boolean allowZero) {
		int randomNumber = 0;

		do {
			Random r = new Random();
			randomNumber = r.nextInt(10);
		} while ((randomNumber == 0) && !allowZero);

		return (char) (randomNumber + '0');
	}

	/**
	 * 去除html标签
	 * 
	 * @param HTMLStr
	 * @return
	 */
	public static String removeHtmlTag(String HTMLStr) {
		if (StringUtils.isBlank(HTMLStr)) {
			return "";
		}

		String htmlStr = HTMLStr;
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
			String regEx_html = "<[^>]+>";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll("");
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll("");
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll("");
			textStr = htmlStr.replaceAll(" ", "");
			// textStr = htmlStr.replaceAll("<", "<");
			// textStr = htmlStr.replaceAll(">", ">");
			// textStr = htmlStr.replaceAll("®", "®");
			// textStr = htmlStr.replaceAll("&", "&");
			textStr = StringUtils.replace(textStr, "&nbsp;", "");
			textStr = StringUtils.replace(textStr, "\r", "");
			textStr = StringUtils.replace(textStr, "\n", "");
			textStr = StringUtils.trim(textStr);

		} catch (Exception e) {
			LOG.error("", e);
		}
		return textStr;
	}

	public static String UTF8TOGBK(String str) {
		try {
			return new String(str.getBytes("utf-8"), "GBK");
		} catch (UnsupportedEncodingException e) {
			LOG.error("utf8 到 GBK 转码失败", e);
		}
		return str;
	}

	public static String GBKTOUTF8(String str) {
		try {
			return new String(str.getBytes("gbk"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error("GBK到 utf8 转码失败", e);
		}
		return str;
	}

	public static String ISOToUTF8(String str) {
		try {
			if (StringUtils.isEmpty(str))
				return "";
			return new String(str.trim().getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error("iso-8859-1到 utf8 转码失败", e);
		}
		return str;
	}

	public static String parseRole(Integer userRole) {
		switch (userRole) {
		case 1:
			return "系统管理员";
		case 2:
			return "销售人员";
		case 3:
			return "导游";
		case 4:
			return "业务员";
		default:
			break;
		}
		return "";
	}

	/**
	 * 获得给定时间的零点
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateStart(Date date) {
		return FORMAT.format(date);
	}

	/**
	 * 获得给定时间n天后的零点
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static String getDateStart(Date date, int n) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.DAY_OF_MONTH, n);
		return FORMAT.format(gc.getTime());
	}

	public static String datetoformtter(String s) {
		String formmatterdate = "";
		if (s.length() >= 12) {
			String year = s.substring(0, 4) + "-";
			String month = s.substring(4, 6) + "-";
			String day = s.substring(6, 8) + "/";
			String minite = s.substring(8, 10) + ":";
			String second = s.substring(10, 12);
			formmatterdate = year + month + day + minite + second;
		}
		return formmatterdate;
	}

	public static String stringBuilder(String... strings) {
		StringBuilder sb = new StringBuilder();
		if (strings != null && strings.length > 0) {
			for (String s : strings)
				sb.append(s);
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param date
	 *            yyyyMMddHHmm
	 * @return
	 */
	public static String dateStore(Date date) {
		if (date == null)
			return "";
		return FORMAT_DATE.format(date);
	}

	public static String dateView(Date date) {
		if (date == null)
			return "";
		return FORMAT_VIEW.format(date);
	}

	/**
	 * 
	 * @param date
	 *            yyyyMMddHHmm
	 * @return
	 */
	public static String dateView(String date) {
		if (StringUtils.isEmpty(date))
			return "";
		try {
			return FORMAT_VIEW.format(FORMAT_DATE.parse(date));
		} catch (ParseException e) {
		}
		return "";
	}

	public static String msgTypeToShowName(String msgType) {
		try {
			int mt = Integer.parseInt(msgType);
			switch (mt) {
			case 1:
				return "短信发送";
			case 2:
				return "满意度调查";
			case 3:
				return "业务宣传";
			case 4:
				return "导游发送";
			}
		} catch (Exception e) {
		}
		return "";
	}

	public static boolean isPhoneNumber(String phoneNumber) {
		Matcher matcher = PHONE_PATTERN.matcher(phoneNumber);
		return matcher.matches();
	}
	public static boolean isPersonName(String realName){
	    String name=StringUtils.trim(realName);
	    if(StringUtils.isBlank(name)){
	        return false;
	    }
	    Matcher matcher =REAL_NAME_PATTERN.matcher(name);
	    return matcher.matches();
	}

	public static String generateSessionId() {
		String randKey = UUID.randomUUID().toString() + "_"
				+ System.currentTimeMillis();
		String s = SHA256Util.hash(randKey);
		return s;
	}

	public static String timeFormat(String t) {
		String s = "";
		if (t != null && t.length() == 4) {
			s = t.substring(0, 2) + ":" + t.substring(2, 4);
		}
		return s;
	}
	
	public static String judge(String str){
		String res = str==null?"":str;
		return res;
	}
	
	/**
	 * 生成一个验证码,去除0,o,1,l,2,z
	 * @author Zexin.Wang
	 * @param num 验证码位数
	 * @return 验证码
	 */
	public static String getVerifyCode(int num){
		String[] beforeShuffle = new String[] {
		"3", "4", "5", "6", "7", "8", "9",
		"a", "b", "d", "c", "e", "f", "g", "h", "i", "j", 
		"3","4", "5", "6", "7", "8", "9",
		"k",  "m", "n", "p", "q", "r", "s", "t","u", "v", "w", "x", "y" };
		List<String> list = Arrays.asList(beforeShuffle);
		Collections.shuffle(list);
		int size = list.size();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < num ; i++) {
			Random r = new Random();
			sb.append(list.get(r.nextInt(size)));
		}
		String result = sb.toString();

		return result;
	}
}
