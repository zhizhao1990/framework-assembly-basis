package com.cmc.dp.pattern.singleton;

/**
 * 创建型模式：单例模式
 * 
 * <p> 线程安全懒汉式
 * <p> 这种方式具备很好的lazy loading，能够在多线程中很好的工作，但是，效率很低，99%情况下不需要同步。
 * 
 * <ul>
 * <li> 是否延迟初始化：是
 * <li> 是否多线程安全：是
 * </ul>
 * 
 * @author Thomas Lee
 * @version 2017年2月18日 下午2:23:44
 */
public class SingletonPattern2 {

    private static SingletonPattern2 instance;

    private SingletonPattern2() {
    }

    public static synchronized SingletonPattern2 getInstance() {
        if (null == instance) {
            instance = new SingletonPattern2();
        }
        return instance;
    }

}