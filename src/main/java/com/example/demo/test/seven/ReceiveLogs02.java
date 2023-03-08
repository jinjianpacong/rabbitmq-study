package com.example.demo.test.seven;

import com.example.demo.utils.RabbitUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 2014080902211金泂枞
 * @Date: 2023/01/07/21:51
 * @Description:
 */
public class ReceiveLogs02 {
    public static final String EXCHANGE_NAME = "topic_logs";
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitUtils.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        String queueName = channel.queueDeclare().getQueue();

        channel.queueBind(queueName, EXCHANGE_NAME, "lazy.#");
        DeliverCallback deliverCallback=(consumerTag, message)->{
            String s = "接收到消息" + new String(message.getBody())+",绑定建为"+message.getEnvelope().getRoutingKey();
            System.out.println(s);
            channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
        };
        CancelCallback cancelCallback=(consumerTag)->{

        };
        channel.basicConsume(queueName, false, deliverCallback,cancelCallback);


    }
}
