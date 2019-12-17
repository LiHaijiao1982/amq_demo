package com.example.amqdemo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MqTest {
    private static String mqUrl = "tcp://172.17.117.12:61616";

    @Test
    public void producer() {
        try {
            //1、创建工厂连接对象，需要制定ip和端口号
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(mqUrl);
            //2、使用连接工厂创建一个连接对象
            Connection connection = connectionFactory.createConnection();
            //3、开启连接
            connection.start();
            connection.setClientID("");
            //4、使用连接对象创建会话（session）对象
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
            Queue queue = session.createQueue("test-queue");
            //6、使用会话对象创建生产者对象
            MessageProducer producer = session.createProducer(queue);
            //7、使用会话对象创建一个消息对象
            TextMessage textMessage = session.createTextMessage("hello!test-queue");
            //8、发送消息
            producer.send(textMessage);
            //9、关闭资源
            producer.close();
            session.close();
            connection.close();
        } catch (Exception e) {

        }

    }

    public void consumer() {
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(mqUrl);
            Connection connection = connectionFactory.createConnection();
            connection.setClientID("clientId");
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic destination = session.createTopic("test-topic");
            MessageConsumer consumer = session.createDurableSubscriber(destination, "consumerId");
            // topicSubscriber.setMessageListener(new DestinationListener());

            while(true){
                Message message = consumer.receive(10000);

                if(message == null){
                    break;
                }

                if(message instanceof TextMessage){
                    TextMessage textmessage = (TextMessage)message;
                    System.out.println(textmessage.getText());
                }

            }

            connection.close();
            session.close();
            connection.close();

        } catch (Exception e) {

        }
    }
}
