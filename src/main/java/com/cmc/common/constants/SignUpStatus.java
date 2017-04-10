package com.cmc.common.constants;

/**
 * 注册状态枚举
 * @author lcb
 */
public enum SignUpStatus {

	SUCCESS("0", "注册成功"), USERNAME_ALREADY_EXIST("100", "用户名已存在"), UNKNOWN_ERROR("199", "发生未知错误");

	private String code;
	private String desc;

	public String getCode() {
		return this.code;
	}

	public String getDesc() {
		return this.desc;
	}

	private SignUpStatus(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

}