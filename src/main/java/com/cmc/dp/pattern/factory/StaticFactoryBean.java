package com.cmc.dp.pattern.factory;

import java.util.Random;

public class StaticFactoryBean {

    public static Integer createRandom() {
        return new Random().nextInt();
    }

}