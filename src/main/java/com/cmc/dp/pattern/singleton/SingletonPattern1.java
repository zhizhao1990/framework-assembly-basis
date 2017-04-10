package com.cmc.dp.pattern.singleton;

/**
 * 创建型模式：单例模式
 * 
 * <p> 线程不安全懒汉式
 * <p> 这种方式是最基本的实现方式，这种实现最大的问题就是不支持多线程。因为没有加锁<code>synchronized</code>，所以严格意义上它并不算单例模式。
 * 
 * <ul>
 * <li> 是否延迟初始化：是
 * <li> 是否多线程安全：否
 * </ul>
 * 
 * @author Thomas Lee
 * @version 2017年2月18日 下午2:23:44
 */
public class SingletonPattern1 {

    private static SingletonPattern1 instance;

    private SingletonPattern1() {
    }

    public static SingletonPattern1 getInstance() {
        if (null == instance) {
            instance = new SingletonPattern1();
        }
        return instance;
    }

}