package com.example.amqdemo.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqQueueConsumer2 {
    private final static Logger logger = LoggerFactory.getLogger(ActiveMqQueueConsumer2.class);

    @Value("${activemq.queue_name}")
    private String queueName;

    @JmsListener(destination = "${activemq.queue_name}")
    public void receiveQueue(String msg) {
        logger.info("ActiveMqQueueConsumer2 receive queue: {} message: {}", queueName, msg);
    }
}
