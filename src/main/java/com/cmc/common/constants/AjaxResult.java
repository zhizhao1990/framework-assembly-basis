package com.cmc.common.constants;

import java.io.Serializable;

import net.sf.json.JSONObject;

import com.cmc.user.facade.model.UserModel;

/**
 * AJAX请求返回数据的包装类（静态工厂）.
 * @author UCMED
 * @date 2017年3月7日 上午9:54:32
 */
public final class AjaxResult extends AbstractAjaxResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private AjaxResult() {
        super();
    }

    private AjaxResult(int code, String msg, JSONObject data) {
        super(code, msg, data);
    }

    /**
     * 返回AJAX通用结果实例
     * @author UCMED
     * @date 2017年3月7日 上午10:59:40
     * @param code 返回码
     * @param msg 返回信息
     * @return 返回结果
     */
    public static AjaxResult getInstance(int code, String msg) {
        return AjaxResult.getInstance(code, msg, null);
    }

    /**
     * 返回AJAX通用结果实例
     * @author UCMED
     * @date 2017年3月7日 上午11:00:37
     * @param code 返回码
     * @param msg 返回信息
     * @param data 返回JSON信息
     * @return 返回结果
     */
    public static AjaxResult getInstance(int code, String msg, JSONObject data) {
        return new AjaxResult(code, msg, data);
    }

    /**
     * 返回AJAX成功结果实例
     * @author UCMED
     * @date 2017年3月7日 上午11:02:23
     * @param msg 返回信息
     * @return 返回结果
     */
    public static AjaxResult getSuccessInstance(String msg) {
        return AjaxResult.getInstance(ResCode.OK.getCode(), msg, null);
    }

    /**
     * 返回AJAX成功结果实例
     * @author UCMED
     * @date 2017年3月7日 上午11:02:45
     * @param msg 返回信息
     * @param data 返回JSON信息
     * @return 返回结果
     */
    public static AjaxResult getSuccessInstance(String msg, JSONObject data) {
        return AjaxResult.getInstance(ResCode.OK.getCode(), msg, data);
    }

    public static AjaxResult getSuccessInstance(String msg, Object... obj) {
        for (int i = 0; i < obj.length; i++) {
            System.out.println(obj.getClass());
        }
        return null;
    }
    
    public static void main(String[] args) {
        getSuccessInstance("msg", new String(), new UserModel());
        System.out.println(java.lang.String.class);
    }
    
    /**
     * 返回AJAX失败结果实例
     * @author UCMED
     * @date 2017年3月7日 上午11:02:49
     * @param msg 返回信息
     * @return 返回结果
     */
    public static AjaxResult getFailInstance(String msg) {
        return AjaxResult.getInstance(ResCode.SERVER_ERROR.getCode(), msg);
    }

    /**
     * 返回AJAX失败结果实例
     * @author UCMED
     * @date 2017年3月7日 上午11:02:52
     * @param code 返回码
     * @param msg 返回信息
     * @return 返回结果
     */
    public static AjaxResult getFailInstance(int code, String msg) {
        if (code == ResCode.OK.getCode()) {
            throw new IllegalArgumentException(ILLEGAL_CODE);
        }
        return AjaxResult.getInstance(code, msg);
    }

    /**
     * 返回AJAX失败结果实例
     * @author UCMED
     * @date 2017年3月7日 上午11:02:56
     * @param code 返回码
     * @param msg 返回信息
     * @param data 返回JSON信息
     * @return 返回结果
     */
    public static AjaxResult getFailInstance(int code, String msg, JSONObject data) {
        if (code == ResCode.OK.getCode()) {
            throw new IllegalArgumentException(ILLEGAL_CODE);
        }
        return AjaxResult.getInstance(code, msg, data);
    }

}