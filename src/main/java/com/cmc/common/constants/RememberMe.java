package com.cmc.common.constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 是否“记住我”枚举
 * 
 * @author Thomas
 * @since 2016年11月20日 下午7:14:22
 */
public enum RememberMe {

    YES("1", "是"), NO("0", "否");

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(RememberMe.class);

    private String code;
    private String desc;

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    private RememberMe(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 枚举解析
     * 
     * @author Thomas Lee
     * @since 2016年12月28日 下午4:10:43
     * @return null，如果没有参数code对应的的枚举，否则返回对应枚举。
     */
    public static RememberMe parseCode(String code) {
        RememberMe[] rememberMes = RememberMe.values();
        for (int i = 0; i < rememberMes.length; i++) {
            RememberMe current = rememberMes[i];
            if (current.getCode().equals(code)) {
                return current;
            }
        }
        return null;
    }

}