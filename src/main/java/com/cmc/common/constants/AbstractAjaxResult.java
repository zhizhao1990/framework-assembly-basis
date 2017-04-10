package com.cmc.common.constants;

import net.sf.json.JSONObject;

/**
 * AJAX通用返回结果抽象类
 * @author UCMED
 * @version 2017年3月7日 上午10:56:46
 */
public abstract class AbstractAjaxResult {

    protected static final String ILLEGAL_CODE = "返回代码非法.";

    /** 返回码 */
    private int code;

    /** 返回信息 */
    private String msg;

    /** 返回JSON信息 */
    private JSONObject data;

    protected AbstractAjaxResult() {
    }

    protected AbstractAjaxResult(int code, String msg, JSONObject data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回AJAX通用结果实例
     * @author UCMED
     * @date 2017年3月7日 上午10:57:17
     * @param code 返回码
     * @return 返回结果
     */
    public static AjaxResult getInstance(ResCode code) {
        return AjaxResult.getInstance(code.getCode(), code.getDesc());
    }

    public static AjaxResult getSuccessInstance() {
        return AjaxResult.getInstance(ResCode.OK);
    }

    /**
     * 返回AJAX失败结果实例
     * @author UCMED
     * @date 2017年3月7日 上午10:58:22
     * @param res 返回码
     * @return 返回结果
     */
    public static AjaxResult getFailInstance(ResCode res) {
        if (ResCode.OK.equals(res)) {
            throw new IllegalArgumentException(ILLEGAL_CODE);
        }
        return AjaxResult.getInstance(res.getCode(), res.getDesc());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

}