package com.example.demo.consumer;

import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 2014080902211金泂枞
 * @Date: 2023/01/06/19:41
 * @Description:
 */
@SpringBootTest
public class Test1 {
    public static final String QUEUE_NAME = "hello";

//    public static void main(String[] args) throws IOException {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        factory.setPort(5672);
//        factory.setVirtualHost("/");
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//        //发送一个消息
//        Connection connection = null;
//        try {
//            connection = factory.newConnection();
//        } catch (IOException | TimeoutException e) {
//            e.printStackTrace();
//
//        }
//        Channel channel = connection.createChannel();
//        /**
//         * basicConsumer的每个参数意思
//         * 1.队列名称
//         * 2.消费成功后是否要自动应答，true为自动应答，false为手动应答
//         * 3.消息传达完成后的回调
//         * 4.消费者取消消费的回调
//         */
//        channel.basicConsume(QUEUE_NAME, true, (consumerTag,  message)->{
//            String s = new String(message.getBody());
//            System.out.println(s);
//        }, consumerTag->{
//
//        });
//
//    }
    public static void main(String[] args) {
//        new Socket("localhost")
    }
}
