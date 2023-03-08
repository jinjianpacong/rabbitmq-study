package com.example.demo;


import com.example.demo.utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

@SpringBootTest
class Demo1ApplicationTests {
    public static final String QUEUE_NAME="hello";
    public static final String ACK_QUEUE="ack_queue";
    @Test
    public void test() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("guest");
        factory.setPassword("guest");
        //发送一个消息
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //queueDeclare的每个参数意思
        /**
         * 1.队列名称
         * 2.队列里面的消息是否持久化，默认情况消息存储在内存中
         * 3.是否独占队列，只能有一个消费者监听这队列,true:独占队列，false:不独占
         * 4.是否在不使用的时候自动删除队列
         * 5.其他参数
         */
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        /**
         * 1.交换机名称
         * 2.队列名称
         * 3.传递消息额外设置
         * 4.消息的具体内容
         */
        String message = "hello world";
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("消息发送成功");
//        channel.close();
        connection.close();
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitUtils.getChannel();
        channel.confirmSelect();
        Scanner scanner = new Scanner(System.in);
        channel.queueDeclare(ACK_QUEUE,true,false,false,null);
        String mess=new String();
        while(scanner.hasNext())
        {
            mess=scanner.nextLine();

            channel.basicPublish("",ACK_QUEUE, MessageProperties.PERSISTENT_TEXT_PLAIN,mess.getBytes());
        }

    }

}
