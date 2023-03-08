package com.example.demo.test.four;

import com.example.demo.utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 2014080902211金泂枞
 * @Date: 2023/01/07/15:51
 * @Description:
 */
public class Publisher01 {
    public static final String ACK_QUEUE="ack_queue";
    public static final Integer MESSAGE_COUNT=10000;
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //发布10000个单独确认消息，耗时为1429ms
//        fun1();
//        发布10000个单独确认消息，耗时为231ms
//        发布10000个单独确认消息，耗时为355ms
//        fun2();
//        发布10000个单独确认消息，耗时为160ms
        fun3();
    }
    public static void fun1() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitUtils.getChannel();
        channel.queueDeclare(ACK_QUEUE,true,false,false,null);
        channel.confirmSelect();
        String queueName1= UUID.randomUUID().toString();
        long start = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message=i+"";
            channel.basicPublish("", queueName1, null,message.getBytes() );
            boolean flag = channel.waitForConfirms();
            if(flag)
            {
                System.out.println("消息发布成功");
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("发布"+MESSAGE_COUNT+"个单独确认消息，耗时为"+(end-start)+"ms");
    }

    public static void fun2() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitUtils.getChannel();
        channel.queueDeclare(ACK_QUEUE,true,false,false,null);
        channel.confirmSelect();
        String queueName1= UUID.randomUUID().toString();
        long start = System.currentTimeMillis();
        Integer count=0;
        Integer batchSize=1000;
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message=i+"";
            channel.basicPublish("", queueName1, null,message.getBytes() );
            count++;
            if(count.equals(batchSize))
            {
                channel.waitForConfirms();
                count=0;
            }
        }
        if(count>0)
        {
            channel.waitForConfirms();
        }
        long end = System.currentTimeMillis();
        System.out.println("发布"+MESSAGE_COUNT+"个单独确认消息，耗时为"+(end-start)+"ms");
    }
    public static void fun3() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitUtils.getChannel();
        String queueName1= UUID.randomUUID().toString();
        channel.queueDeclare(queueName1,true,false,false,null);
        //开启发布确认
        channel.confirmSelect();

        /**
         *  线程安全有序的一个哈希表  适合高并发的情况下使用
         *  1.轻松的将序号与消息进行关联
         *  2.轻松批量删除条目  只要给到序号就可以了
         *  3.支持高并发 因为是线程安全的
         */
        ConcurrentSkipListMap<Long,String> hashMap=new ConcurrentSkipListMap<>();
        //确认的回调函数
        ConfirmCallback ackCallback=(deliveryTag, multiple)->{
            //multiple为true时，表示批量确认
            if(multiple)
            {
                //删除小于等于deliveryTag的所有条目
                ConcurrentNavigableMap<Long, String> headMap = hashMap.headMap(deliveryTag);
                //删除
                headMap.clear();
            }else {
                // 删除指定的条目
                hashMap.remove(deliveryTag);
            }
            System.out.println("确认的消息" + deliveryTag);
        };
        //未确认的回调函数
        ConfirmCallback nackCallback=(deliveryTag, multiple)->{
            String s = hashMap.get(deliveryTag);
            System.out.println("未确认的消息" + deliveryTag+"消息内容为"+s);
        };
        //设置确认监听器
        channel.addConfirmListener(ackCallback,nackCallback);

        long start = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message=i+"";
            channel.basicPublish("", queueName1, null,message.getBytes() );
            hashMap.put(channel.getNextPublishSeqNo(),message);
        }

        long end = System.currentTimeMillis();
        System.out.println("发布"+MESSAGE_COUNT+"个单独确认消息，耗时为"+(end-start)+"ms");
    }
}
