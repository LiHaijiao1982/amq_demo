package com.example.amqdemo.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqTopicConsumer2 {
    private static Logger log = LoggerFactory.getLogger(ActiveMqTopicConsumer2.class);

    @Value("${activemq.topic_name}")
    private String topicName;

    // @JmsListener(destination = "${activemq.topic_name}", containerFactory = "jmsListenerContainerTopic")
    @SendTo("return-topic")
    public String receiveTopic(String message) {
        log.info("ActiveMqTopicConsumer2 receive topic:{}, message: {}", topicName, message);
        return message;
    }
}
