package com.example.amqdemo.virtual;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Topic;

/**
 *
 */
@Component
public class ActiveMqVirtualTopicProducer {
    private static Logger log = LoggerFactory.getLogger(ActiveMqVirtualTopicProducer.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Resource(name = "virtualTopic")
    private Topic topic;

    public void sendMessage(String message) {
        log.info("ActiveMqVirtualTopicProducer send message: {}", message);
        jmsMessagingTemplate.convertAndSend(topic, message);
    }

    @JmsListener(destination = "return-virtual-topic")
    public void Message(String message) {
        log.info("ActiveMqVirtualTopicProducer receive return-virtual-topic：{}", message );
    }
}
