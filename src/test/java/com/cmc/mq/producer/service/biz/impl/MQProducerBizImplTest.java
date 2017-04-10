package com.cmc.mq.producer.service.biz.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cmc.mq.producer.facade.service.MQProducer;

/**
 * ActiveMQ Producer Test
 * @author Thomas Lee
 * @version 2017年3月12日 下午12:33:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:config/spring/spring-context.xml" })
public class MQProducerBizImplTest {

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