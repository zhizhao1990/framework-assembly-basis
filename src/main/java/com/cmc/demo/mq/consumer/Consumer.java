package com.cmc.demo.mq.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 消息消费者.
 * @author Thomas Lee
 * @version 2017年4月14日 下午2:27:49
 */
public class Consumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/config/spring-context.xml");
        context.start();
        context.stop();
        context.close();
    }

}