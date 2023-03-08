package com.example.demo.test.seven;

import com.example.demo.utils.RabbitUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 2014080902211金泂枞
 * @Date: 2023/01/07/22:10
 * @Description:
 */
public class Publisher01 {
    public static final String EXCHANGE_NAME = "topic_logs";
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitUtils.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext())
        {
            String routeKey=scanner.nextLine();
            String message = scanner.nextLine();
            channel.basicPublish(EXCHANGE_NAME, routeKey, null,message.getBytes());
        }



    }
}
