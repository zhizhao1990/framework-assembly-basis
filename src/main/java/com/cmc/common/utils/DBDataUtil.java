package com.cmc.common.utils;

import org.apache.commons.lang.StringUtils;

import com.cmc.common.constants.Sex;

public class DBDataUtil {

	public static String transSexToFact(String sex) {
		String fact = null;
		if (StringUtils.equals(Sex.MALE.getCode(), sex)) {
			fact = "男性";
		} else if (StringUtils.equals(Sex.FEMALE.getCode(), sex)) {
			fact = "女性";
		} else if (StringUtils.equals(Sex.SECRET.getCode(), sex)) {
			fact = "保密";
		}
		return fact;
	}
	
}