package com.cmc.dp.pattern.factory;

public interface ShapeFactory {

    /**
     * 获取图形
     * 
     * @author Thomas Lee
     * @version 2017年2月20日 上午10:03:04
     */
    public Shape getShape(ShapeEnum shapeEnum);

}