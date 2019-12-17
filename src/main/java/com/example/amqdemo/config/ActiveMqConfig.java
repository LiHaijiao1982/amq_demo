package com.example.amqdemo.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
public class ActiveMqConfig {

    @Value("${activemq.queue_name}")
    private String queueName;
    @Value("${activemq.topic_name}")
    private String topicName;
    @Value("${activemq.virtual_topic_name}")
    private String virtualTopicName;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(queueName);
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic(topicName);
    }

    @Bean(name = "virtualTopic")
    public Topic virtualTopic() {
        return new ActiveMQTopic(virtualTopicName);
    }

    @Autowired
    private ConnectionFactory connectionFactory;

    /**
     * topic模式的ListenerContainer
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic() {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

    /**
    * 以下是手动创建mq的连接，利用springboot的自动配置替换手动方式
     */

    /*@Value("${spring.activemq.broker-url}")
    private String brokeUrl;
    @Value("${spring.activemq.user}")
    private String username;
    @Value("${spring.activemq.password}")
    private String password;

    @Bean(name = "activeMQConnectionFactory")
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokeUrl);
        connectionFactory.setUserName(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean("jmsMessagingTemplate")
    public JmsMessagingTemplate jmsMessageTemplate(){
        return new JmsMessagingTemplate(connectionFactory());
    }*/

}
