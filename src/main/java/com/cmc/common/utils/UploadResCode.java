package com.cmc.common.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author ht-bianyatian
 *
 */
public enum UploadResCode {
    UPLOAD_SUCCESS("0", "上传成功"),
    UPLOAD_FAILED("1", "上传失败"),
    FILE_NOT_EXISTS("2", "文件不存在"),
    FILE_DELETE_FAILED("3", "文件删除失败"),
    PROCESS_ERROR("4", "进程错误"),
    CODE_FAILED("6", "编码错误"),
    PDF_TO_HTML_FAILED("7", "pdf转h5失败"),
    COS_UPLOAD_FAILED("8", "COS完成上传失败"),
    COS_SLICE_UPLOAD_FAILED("9", "cos分页上传失败");
    
    private String code;
    private String desc;

    private UploadResCode(String v, String description) {
        this.code = v;
        this.desc = description;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
;
    /**
     * @Description 根据资源类型对应的 Code 查询资源类型枚举值.
     * @param v
     *            资源类型的 Code.
     * @return 对就有资源类型枚举值，如不存在对应的类型则返回 null.
     */
    public static UploadResCode parseByCode(String v) {
        String code = null;
        if(StringUtils.isBlank(v)) {
            return null;
        } else {
            code = v.trim();
        }
        for(UploadResCode r : UploadResCode.values()) {
            if(r.getCode().equals(code)) {
                return r;
            }
        }
        return null;
    }
}
