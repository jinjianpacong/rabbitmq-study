package com.example.demo.test;

import com.example.demo.utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 2014080902211金泂枞
 * @Date: 2023/01/07/9:07
 * @Description:
 */
public class Work01 {
    public static final String QUEUE_NAME = "hello";
    public static final String ACK_QUEUE="ack_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
//        Channel channel = RabbitUtils.getChannel();
//        System.out.println("C2等待消息");
//        channel.basicConsume(QUEUE_NAME, true, (consumerTag,  message)->{
//            String s = new String(message.getBody());
//            System.out.println(s);
//        }, consumerTag->{
//            System.out.println("消费者取消消费的回调");
//        });


        Channel channel = RabbitUtils.getChannel();
        channel.basicQos(2);
        channel.basicConsume(ACK_QUEUE, false, (consumerTag,  message)->{
            String s = new String(message.getBody());
            System.out.println("c1接收消息:"+s);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //手动应答
            channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
            System.out.println("手动应答成功");
        }, consumerTag->{
            System.out.println("消费者取消消费的回调");
        });
    }
}
