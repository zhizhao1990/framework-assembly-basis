package com.cmc.service.proxybean;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * CGLib Proxy
 * 
 * @author HT.LCB
 * @since 2016年11月21日 下午2:45:21
 */
public class CGLIBProxy implements MethodInterceptor {

    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object rst = null;
        System.out.println("before method.");
        rst = proxy.invokeSuper(obj, args);
        System.out.println("after method.");
        return rst;
    }

    public static void main(String[] args) {
        ProxyBeanService proxyBeanServiceCGLibProxy = (ProxyBeanService) new CGLIBProxy().getInstance(new ProxyBeanServiceImpl());
        proxyBeanServiceCGLibProxy.save("LCB");
    }

}