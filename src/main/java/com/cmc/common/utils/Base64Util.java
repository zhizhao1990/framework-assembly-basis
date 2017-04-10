package com.cmc.common.utils;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {

	public static String encode(String s) {
		try {
			s = new String(Base64.encodeBase64(s.getBytes()));
			s = s.replace("+", "_");
			return s;
		} catch (Exception e) {
			return s;
		}
	}

	public static String decode(String s) {
		try {
			s = s.replace("_", "+");
			return new String(Base64.decodeBase64(s.getBytes()));
		} catch (Exception e) {
			return s;
		}
	}

}