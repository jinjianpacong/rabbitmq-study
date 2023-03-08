package com.example.demo.test.eight;

import com.example.demo.utils.RabbitUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 2014080902211金泂枞
 * @Date: 2023/01/08/11:50
 * @Description:
 */
public class Producer01 {
    public static final String NORMAL_EXCHANGE = "normal_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitUtils.getChannel();
        AMQP.BasicProperties properties = new AMQP.BasicProperties();
//        AMQP.BasicProperties build = properties.builder().expiration("8000").build();
//        channel.basicPublish(NORMAL_EXCHANGE, "zhangsan",build , "123".getBytes());
//        build = properties.builder().expiration("2000").build();
//        channel.basicPublish(NORMAL_EXCHANGE, "zhangsan",build , "456".getBytes());
        for (int i = 0; i < 10; i++) {

            String str="info"+i;

            channel.basicPublish(NORMAL_EXCHANGE, "zhangsan",null , str.getBytes());
        }

    }
}
