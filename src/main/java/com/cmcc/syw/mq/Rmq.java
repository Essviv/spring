package com.cmcc.syw.mq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rmq测试
 *
 * Created by sunyiwei on 2017/3/9.
 */
public class Rmq {
    public static void main(String[] args) throws IOException, TimeoutException {
        final String HOST = "172.23.27.214";
        final int PORT = 5672;
        final String USERNAME = "guest";
        final String PASSWORD = "guest";
        final String QUEUE_NAME = "batch.present.queue";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        factory.setAutomaticRecoveryEnabled(true);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");

                if (message.equalsIgnoreCase("cancel")) {
                    getChannel().basicCancel(consumerTag);
                    return;
                }

                try {
                    System.out.println(" [x] Received '" + message + "'");
                } finally {
                    System.out.println(" [x] Done");
                }
            }
        };
        boolean autoAck = true; // acknowledgment is covered below
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }
}
