package com.cmc.dp.pattern.factory;

public class FactoryPatternDemo {

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactoryImpl();
        Shape rectangle = shapeFactory.getShape(ShapeEnum.RECTANGLE);
        Shape circle = shapeFactory.getShape(ShapeEnum.CIRCLE);
        Shape square = shapeFactory.getShape(ShapeEnum.SQUARE);
        rectangle.draw();
        circle.draw();
        square.draw();
    }

}