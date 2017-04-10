package com.cmc.service.proxybean;

public class ProxyBeanServiceImpl implements ProxyBeanService {

    @Override
    public boolean save(String name) {
        System.out.println("save business logic ..." + name);
        return false;
    }

}