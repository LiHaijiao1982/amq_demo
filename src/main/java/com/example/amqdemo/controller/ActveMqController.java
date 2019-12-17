package com.example.amqdemo.controller;

import com.example.amqdemo.queue.ActiveMqQueueProducer;
import com.example.amqdemo.topic.ActiveMqTopicProducer;
import com.example.amqdemo.virtual.ActiveMqVirtualTopicProducer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Topic;


@RestController
@RequestMapping("/activemq")
public class ActveMqController {
    private static Logger log = LoggerFactory.getLogger(ActveMqController.class);
    @Autowired
    private ActiveMqTopicProducer activeMqTopicProducer;
    @Autowired
    private ActiveMqQueueProducer activeMqQueueProducer;
    @Autowired
    private ActiveMqVirtualTopicProducer activeMqVirtualTopicProducer;

    @Value("${config.path}")
    private String config;

    @RequestMapping("/queue")
    public void queueMessage(@RequestParam("msg") String msg) {
        log.info("config.path: {}", config);
        log.info("Controller receive queue msg: {}", msg);
        activeMqQueueProducer.sendMessage(msg);
    }

    @RequestMapping("/topic")
    public void topicMessage(@RequestParam("msg") String msg) {
        log.info("Controller receive topic msg: {}", msg);
        activeMqTopicProducer.sendMessage(msg);
    }

    @RequestMapping("/virtualTopic")
    public void virtualTopicMessage(@RequestParam("msg") String msg) {
        log.info("Controller receive virtualTopic msg: {}", msg);
        activeMqVirtualTopicProducer.sendMessage(msg);
    }
}
