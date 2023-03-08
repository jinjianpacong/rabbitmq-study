package com.example.demo.test.eight;

import com.example.demo.utils.RabbitUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 2014080902211金泂枞
 * @Date: 2023/01/08/10:58
 * @Description:
 */
public class Consumer01 {
    public static final String NORMAL_EXCHANGE = "normal_exchange";
    public static final String DEAD_EXCHANGE = "dead_exchange";
    public static final String NORMAL_QUEUE = "normal_queue";
    public static final String DEAD_QUEUE = "dead_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitUtils.getChannel();
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);
        HashMap<String, Object> arguments = new HashMap<>();
//        arguments.put("x-message-ttl", 10000);
        arguments.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        arguments.put("x-dead-letter-routing-key","lisi");
        channel.queueDeclare(NORMAL_QUEUE,false,false,false,arguments);
        channel.queueDeclare(DEAD_QUEUE,false,false,false,null);
        channel.queueBind(NORMAL_QUEUE,NORMAL_EXCHANGE, "zhangsan");
        channel.queueBind(DEAD_QUEUE,DEAD_EXCHANGE, "lisi");
        System.out.println("等待接收消息......");
        DeliverCallback deliverCallback=(consumerTag,message)->{
            String s = new String(message.getBody());
            if ("info5".equals(s))
            {
                System.out.println("Consumer01接收的消息是" + new String(message.getBody())+"此消息被拒绝");
                channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
            }
           else
            {
                System.out.println("Consumer01接收的消息是" + new String(message.getBody())+"已接收");
                channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
            }
        };
        CancelCallback cancelCallback=(consumerTag)->{

        };
       channel.basicConsume(NORMAL_QUEUE, false, deliverCallback,cancelCallback);
    }
}
