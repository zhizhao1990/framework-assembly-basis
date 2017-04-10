package com.cmc.mq.consumer.service.biz.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ActiveMQ Consumer Test
 * @author Thomas Lee
 * @version 2017年3月12日 下午12:32:57
 */
public class MQConsumerBizImplTest {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/config/spring-context.xml");
        context.start();
        context.stop();
        context.close();

    }

}