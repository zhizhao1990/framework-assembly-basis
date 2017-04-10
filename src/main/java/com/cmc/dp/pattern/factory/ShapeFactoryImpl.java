package com.cmc.dp.pattern.factory;

public class ShapeFactoryImpl implements ShapeFactory {

    private static final String ERR_MSG_ARG_NON_SUPPORT_SHAPE = "不支持的形状。";

    @Override
    public Shape getShape(ShapeEnum shapeEnum) {
        if (shapeEnum.equals(ShapeEnum.RECTANGLE)) {
            return new Rectangle();
        } else if (shapeEnum.equals(ShapeEnum.CIRCLE)) {
            return new Circle();
        } else if (shapeEnum.equals(ShapeEnum.SQUARE)) {
            return new Square();
        } else {
            System.out.println(ERR_MSG_ARG_NON_SUPPORT_SHAPE);
            return null;
        }
    }
    
}