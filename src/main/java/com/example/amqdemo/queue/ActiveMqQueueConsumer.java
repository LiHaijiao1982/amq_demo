package com.example.amqdemo.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqQueueConsumer {
    private final static Logger logger = LoggerFactory.getLogger(ActiveMqQueueConsumer.class);

    @Value("${activemq.queue_name}")
    private String queueName;

    @JmsListener(destination = "${activemq.queue_name}")
    public void receiveQueue(String msg) {
        logger.info("ActiveMqQueueConsumer receive queue: {} message: {}", queueName, msg);
    }

}
