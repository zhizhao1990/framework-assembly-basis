package com.cmc.dp.pattern.singleton;

/**
 * 创建型模式：单例模式
 * 
 * <p> 枚举
 * <p> 这种实现方式还没有被广泛采用，但这是实现单例模式的最佳方法。它更简洁，自动支持序列化机制，绝对防止多次实例化。
 * <ul>
 * <li> 是否延迟初始化：是
 * <li> 是否多线程安全：是
 * </ul>
 * 
 * @author Thomas Lee
 * @version 2017年2月18日 下午2:23:44
 */
public enum SingletonPattern6 {

    INSTANCE;

    public void whateverMethod() {
    }

}