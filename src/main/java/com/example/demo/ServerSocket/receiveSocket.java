package com.example.demo.ServerSocket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author dushikang
 * @version 1.0
 * @date 2019/9/18 17:22
 */
public class receiveSocket extends Thread{
    Socket socket;

    public receiveSocket(Socket socket) {
        this.socket = socket;
    }

    public void run(){
        try{
            System.out.println("receive线程正常启动");
            OutputStream  os=socket.getOutputStream();
            //***************接受代码******************
            //*********************************
            System.out.println("接受完毕");
            //os.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
