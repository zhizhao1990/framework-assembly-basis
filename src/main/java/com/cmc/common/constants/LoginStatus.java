package com.cmc.common.constants;

/**
 * 登录状态枚举
 * 
 * @author HT.LCB
 * @version 2016年11月8日 下午5:28:53
 */
public enum LoginStatus {

	SUCCESS("0", "登录成功"), 
	USERNAME_NO_EXIST("100", "用户名不存在"), 
	PASSWORD_ERROR("101", "密码错误"), 
	UNKNOWN_ERROR("199", "发生未知错误");

	private String code;
	private String desc;

	public String getCode() {
		return this.code;
	}


	public String getDesc() {
		return this.desc;
	}

	private LoginStatus(String code, String desc) {
		this.code = code;
		this.desc = desc;

		this.code = code;
		this.desc = desc;
	}

}