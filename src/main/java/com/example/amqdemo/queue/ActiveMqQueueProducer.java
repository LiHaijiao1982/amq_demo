package com.example.amqdemo.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
public class ActiveMqQueueProducer {
    private static Logger log = LoggerFactory.getLogger(ActiveMqQueueProducer.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;

    public void sendMessage(String msg) {
        log.info("ActiveMqQueueProducer send message: {}", msg);
        jmsMessagingTemplate.convertAndSend(queue, msg);
    }
}
