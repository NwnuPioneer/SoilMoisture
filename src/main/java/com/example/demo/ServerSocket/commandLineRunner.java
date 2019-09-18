package com.example.demo.ServerSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.concurrent.Executors.newCachedThreadPool;

/**
 * 项目启动后，启动serverListener类
 * @author dushikang
 * @version 1.0
 * @date 2019/9/18 16:48
 */
@Component
public class commandLineRunner implements CommandLineRunner {
    private HandlerThread handlerThreads;
    @Autowired
    private com.example.demo.mapper.sensorMapper sensorMapper;

    @Autowired
    private com.example.demo.mapper.sensor_typeMapper sensor_typeMapper;

    @Autowired
    private com.example.demo.mapper.batteryMapper batteryMapper;

    @Autowired
    private com.example.demo.mapper.weatherMapper weatherMapper;

    public static final int PORT = 9092;//监听的端口号9092

    @Override
    public void run(String... args) throws Exception {
        System.out.println("开始启动");
        //工程启动后，新建serverListener类，并启动该类用于检测指定端口
        serverListener serverListen=new serverListener();
        serverListen.start();
    }
}
