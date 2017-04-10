package com.cmc.dp.pattern.factory;

/**
 * Shape枚举
 *  
 * @author Thomas Lee
 * @version 2017年2月20日 上午10:02:04
 */
public enum ShapeEnum {

    RECTANGLE("矩形"), CIRCLE("圆形"), SQUARE("正方形");

    private String desc;

    public String getDesc() {
        return desc;
    }

    private ShapeEnum(String desc) {
        this.desc = desc;
    }

}