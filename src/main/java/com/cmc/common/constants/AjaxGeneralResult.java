package com.cmc.common.constants;

import java.io.Serializable;

import net.sf.json.JSONObject;

/**
 * 通用返回结果
 * 
 * @author Thomas Lee
 * @since 2016年12月21日 下午3:21:17
 */
public class AjaxGeneralResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private String info;
    private JSONObject data;

    public String getCode() {
        return this.code;
    }

    public String getInfo() {
        return this.info;
    }

    public JSONObject getData() {
        return this.data;
    }

    private AjaxGeneralResult() {
    }

    private AjaxGeneralResult(String code, String info) {
        this.code = code;
        this.info = info;
        this.data = new JSONObject();
    }

    private AjaxGeneralResult(String code, String info, JSONObject data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public static AjaxGeneralResult getSuccessRst() {
        return new AjaxGeneralResult(IsSuccess.YES.getCode(), null);
    }

    public static AjaxGeneralResult getSuccessRst(String info) {
        return new AjaxGeneralResult(IsSuccess.YES.getCode(), info + IsSuccess.YES.getDesc());
    }

    public static AjaxGeneralResult getFailureRst() {
        return new AjaxGeneralResult(IsSuccess.NO.getCode(), null);
    }

    public static AjaxGeneralResult getFailureRst(String info) {
        return new AjaxGeneralResult(IsSuccess.NO.getCode(), info + IsSuccess.NO.getDesc());
    }

    public static AjaxGeneralResult getGeneralRst(String code, String info) {
        return new AjaxGeneralResult(code, info);
    }

    public static AjaxGeneralResult getGeneralRst(String code, String info, JSONObject data) {
        return new AjaxGeneralResult(code, info, data);
    }

}