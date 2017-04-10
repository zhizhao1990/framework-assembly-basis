package com.cmc.service.proxybean;

public class ProxyBeanServiceStaticProxy implements ProxyBeanService {

    private ProxyBeanService proxyBeanService;

    public ProxyBeanServiceStaticProxy(ProxyBeanService proxyBeanService) {
        this.proxyBeanService = proxyBeanService;
    }

    @Override
    public boolean save(String name) {
        System.out.println("before Class ProxyBeanService ...");
        proxyBeanService.save("LCB");
        System.out.println("after Class ProxyBeanService ...");
        return false;
    }

    public static void main(String[] args) {
        ProxyBeanServiceStaticProxy staticProxy = new ProxyBeanServiceStaticProxy(new ProxyBeanServiceImpl());
        staticProxy.save("TMP");
    }

}