package com.cmc.common.constants;

/**
 * 性别枚举
 * @author lcb
 */
public enum Sex {

	MALE("0", "男性"), FEMALE("1", "女性"), SECRET("2", "保密");

	private String code;
	private String desc;

	public String getCode() {
		return this.code;
	}

	public String getDesc() {
		return this.desc;
	}

	private Sex(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

}