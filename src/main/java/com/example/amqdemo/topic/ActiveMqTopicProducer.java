package com.example.amqdemo.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.Topic;

@Component
public class ActiveMqTopicProducer {
    private static Logger log = LoggerFactory.getLogger(ActiveMqTopicProducer.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Topic topic;

    public void sendMessage(String message) {
        log.info("ActiveMqTopicProducer send message: {}", message);
        jmsMessagingTemplate.convertAndSend(topic, message);
    }

    /**
     * 这里的第二个方法加上了监听注解就可以收到消费者返回的信息，前提消费者要加@SendTo注解，
     * @param message
     */
    @JmsListener(destination = "return-topic")
    public void Message(String message) {
        log.info("ActiveMqTopicProducer receive return-topic：{}", message );
    }

}
