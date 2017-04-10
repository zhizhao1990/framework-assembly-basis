package com.cmc.dp.pattern.singleton;

/**
 * 创建型模式：单例模式
 * 
 * <p> 饿汉式
 * <p> 这种方式比较常用，但容易产生垃圾对象。
 * 
 * <ul>
 * <li> 是否延迟初始化：否
 * <li> 是否多线程安全：是
 * </ul>
 * 
 * @author Thomas Lee
 * @version 2017年2月18日 下午2:23:44
 */
public class SingletonPattern3 {

    private static SingletonPattern3 instance = new SingletonPattern3();

    private SingletonPattern3() {
    }

    public static SingletonPattern3 getInstance() {
        return instance;
    }

}