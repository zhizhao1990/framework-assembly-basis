package com.cmc.mq.producer.service.biz.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.cmc.mq.producer.facade.service.MQProducer;

public class MQProducerBizImpl implements MQProducer {

	private JmsTemplate jmsTemplate;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public void sendMessage(String msg) {
		MyMessageCreator messageCreator = new MQProducerBizImpl().new MyMessageCreator();
		messageCreator.setMsg(msg);
		jmsTemplate.send(messageCreator);
	}

	/**
	 * 自定义MessageCreator
	 * @author Thomas Lee
	 * @date 2017年3月11日 上午12:54:54
	 */
	class MyMessageCreator implements MessageCreator {

		private String msg;

		@Override
		public Message createMessage(Session session) throws JMSException {
			return session.createTextMessage(this.msg);

		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}

}