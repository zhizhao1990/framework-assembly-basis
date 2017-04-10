package com.cmc.common.constants;

/**
 * Enumeration IsSuccess
 * 
 * @author Thomas
 * @since 2016年11月20日 下午7:14:22
 */
public enum IsSuccess {

	YES("1", "成功"), NO("0", "失败");

	private String code;
	private String desc;

	public String getCode() {
		return this.code;
	}

	public String getDesc() {
		return this.desc;
	}

	private IsSuccess(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

}