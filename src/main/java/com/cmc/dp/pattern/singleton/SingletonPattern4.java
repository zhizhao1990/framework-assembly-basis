package com.cmc.dp.pattern.singleton;

/**
 * 创建型模式：单例模式
 * 
 * <p> 双检锁/双重校验锁（DCL，即 double-checked locking）
 * <p> 这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
 * 
 * <ul>
 * <li> 是否延迟初始化：是
 * <li> 是否多线程安全：是
 * </ul>
 * 
 * @author Thomas Lee
 * @version 2017年2月18日 下午2:23:44
 */
public class SingletonPattern4 {

    private volatile static SingletonPattern4 instance;

    private SingletonPattern4() {
    }

    public static SingletonPattern4 getInstance() {
        if (null == instance) {
            synchronized (SingletonPattern4.class) {
                if (null == instance) {
                    instance = new SingletonPattern4();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(SingletonPattern6.INSTANCE);
    }

}