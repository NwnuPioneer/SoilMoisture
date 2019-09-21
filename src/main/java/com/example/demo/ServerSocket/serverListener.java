package com.example.demo.ServerSocket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 用于监听指定IP地址指定端口
 * @author dushikang
 * @version 1.0
 * @date 2019/9/18 17:13
 */
public class serverListener extends Thread{

    //public static final String IP="39.96.18.144";
    public static final int PORT=9092;

    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("serverListener正常监听");
            while(true){
                // 一旦有堵塞, 则表示服务器与客户端获得了连接
                Socket client = serverSocket.accept();
                //将socket传递给 接受socket线程类,并启动线程
                receiveSocket rsThread=new receiveSocket(client);
                rsThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
