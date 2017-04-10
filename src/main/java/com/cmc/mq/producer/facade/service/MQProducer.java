package com.cmc.mq.producer.facade.service;

/**
 * MQ Producer
 * @author Thomas Lee
 * @date 2017年3月11日 上午12:47:38
 */
public interface MQProducer {

	public void sendMessage(String msg);

}