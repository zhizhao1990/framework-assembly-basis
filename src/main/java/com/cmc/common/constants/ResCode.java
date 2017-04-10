package com.cmc.common.constants;

/**
 * @author UCMED
 * @date 2017年3月7日 上午9:52:44
 * <ul> 注意事项：
 * <li> 除非返回code情况对应HTTP状态码，否则不建议自定义和HTTP状态码相同的返回码；</li>
 * <li> 一般情况下，建议返回码从600开始定义.</li>
 * </ul>
 */
public enum ResCode  {

    OK(200, "成功"),
    INVALID_ARGS(400, "请求参数有误"),
    SERVER_ERROR(500, "系统繁忙，请稍后再试"),
    USER_NOT_FOUND(645, "用户不存在"),
    VERSION_ERROR(800, "版本检查失败"),
    ILLEGAL_CHARACTER(12601, "存在非法字符"),
    WORD_NUMBER_OUT_OF_LIMIT(12602, "字数超出限制");

    private int code;
    private String desc;

    ResCode(final int code, final String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}