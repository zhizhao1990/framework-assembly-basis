package com.cmc.demo.javase.serialization;

import java.io.Serializable;

/**
 * 序列化和反序列化所用实体
 * @author Thomas Lee
 * @version 2017年4月5日 上午11:56:46
 */
public class Person implements Serializable {

    private int age;
    private String name;

    // 序列化ID
    private static final long serialVersionUID = -5809782578272943999L;

    public Person() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return age + " " + name;
    }

}