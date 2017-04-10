package com.cmc.service.proxybean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK Proxy
 * 
 * @author Thomas Lee
 * @since 2016年11月21日 下午2:44:54
 * @version 2017年2月14日 上午11:27:44
 */
public class JDKProxy implements InvocationHandler {

    private Object target;

    public Object createProxy(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object rst = null;
        System.out.println("before method.");
        rst = method.invoke(target, args);
        System.out.println("after method.");
        return rst;
    }

    public static void main(String[] args) {
        ProxyBeanService proxyBeanService4JDKProxy = (ProxyBeanService) new JDKProxy().createProxy(new ProxyBeanServiceImpl());
        proxyBeanService4JDKProxy.save("");
    }

}