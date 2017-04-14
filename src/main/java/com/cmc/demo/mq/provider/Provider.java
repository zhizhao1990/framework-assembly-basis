package com.cmc.demo.mq.provider;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cmc.mq.producer.facade.service.MQProducer;
import com.cmc.mq.producer.service.biz.impl.MQProducerBizImplTest;

public class Provider {

    private static final Logger LOG = LoggerFactory.getLogger(MQProducerBizImplTest.class);

    @Autowired
    private MQProducer mqProducer;

    @Test
    public void testSendMessage() {
        try {
            mqProducer.sendMessage("Hello");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

}