package com.cmc.mq.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;

public class ConsumerSessionAwareMessageListener implements SessionAwareMessageListener<Message> {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(ConsumerSessionAwareMessageListener.class);

    @Override
    public synchronized void onMessage(Message message, Session session) throws JMSException {
        // ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
        // final String ms = msg.getText();
        // LOG.info(ms);
    }

}