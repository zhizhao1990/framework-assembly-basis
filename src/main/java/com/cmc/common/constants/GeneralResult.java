package com.cmc.common.constants;

/**
 * 通用返回结果
 * 
 * @author Thomas Lee
 * @since 2016年12月21日 下午3:21:00
 */
public enum GeneralResult {

    YES("1", "成功"), NO("0", "失败");

    private String code;
    private String desc;

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    private GeneralResult(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}