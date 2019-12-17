package com.example.amqdemo.virtual;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqVirtualTopicConsumer2 {
    private static Logger log = LoggerFactory.getLogger(ActiveMqVirtualTopicConsumer2.class);

    @Value("${activemq.virtual_topic_name.consumer}")
    private String topicName;

    @JmsListener(destination = "${activemq.virtual_topic_name.consumer}")
    @SendTo("return-virtual-topic")
    public String receiveTopic(String message) {
        log.info("ActiveMqVirtualTopicConsumer2 receive virtualTopic:{}, message: {}", topicName, message);
        return message;
    }
}
