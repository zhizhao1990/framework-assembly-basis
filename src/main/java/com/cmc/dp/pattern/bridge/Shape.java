package com.cmc.dp.pattern.bridge;

public abstract class Shape {

    protected DrawAPI drawAPI;

    /* （聚合）依赖抽象的DrawAPI，而不是依赖具体类RecCircle和GreenCircle */
    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    public abstract void draw();

}