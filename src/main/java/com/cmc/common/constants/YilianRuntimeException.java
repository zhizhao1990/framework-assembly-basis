package com.cmc.common.constants;

/**
 * 医链系统运行时异常父类.
 * @author plz
 */
public class YilianRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String message;

    public YilianRuntimeException() {
    }

    public YilianRuntimeException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public YilianRuntimeException(ResCode resCode) {
        this(resCode.getCode(), resCode.getDesc());
    }

    public YilianRuntimeException(String message) {
        super(message);
        this.code = ResCode.SERVER_ERROR.getCode();
        this.message = message;
    }

    public YilianRuntimeException(Throwable cause) {
        super(cause);
        this.code = ResCode.SERVER_ERROR.getCode();
        this.message = cause.getMessage();
    }

    public YilianRuntimeException(String message, Throwable cause) {
        super(message, cause);
        this.code = ResCode.SERVER_ERROR.getCode();
        this.message = message;
    }

    public YilianRuntimeException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = ResCode.SERVER_ERROR.getCode();
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
