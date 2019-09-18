package com.example.demo.ServerSocket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.concurrent.Executors.newCachedThreadPool;

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

            ExecutorService exec = newCachedThreadPool();
            HandlerThread handlerThread=new HandlerThread(socket);
            Future f = exec.submit(handlerThread);

            storageData storageD=new storageData(f.get().toString());
            storageD.start();//开始存储数据
            System.out.println("接受完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
