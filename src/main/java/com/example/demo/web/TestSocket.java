package com.example.demo.web;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 2014080902211金泂枞
 * @Date: 2023/01/30/12:09
 * @Description:
 */
@Component
@ServerEndpoint("/myWs")
public class TestSocket {



    /**
     * 连接成功
     *
     * @param session
     */
    @OnOpen
    public void onOpen( Session session) {
        System.out.println("连接成功");
        session.getAsyncRemote().sendText("八嘎呀路");
    }

    /**
     * 连接关闭
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        System.out.println("连接关闭");
    }

    /**
     * 接收到消息
     *
     * @param text
     */
    @OnMessage
    public String onMsg(String text,Session session) throws IOException {

        return "servet 发送：" + text;
    }

}
