package com.example.demo.ServerSocket;

import com.example.demo.dao.battery;
import com.example.demo.dao.sensor;
import com.example.demo.mapper.batteryMapper;
import com.example.demo.mapper.sensorMapper;
import com.example.demo.mapper.sensor_typeMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;


public class HandlerThread implements Callable<String> {
    private String hex;
    private String sensor_type;//传感器类型
    private Socket socket;

    public HandlerThread(Socket client) {
        socket = client;
    }
    //字节转十六进制
    private String bytes(byte[] bytes, int begin, int end) {
//字节转十进制
        StringBuilder hexBuilder = new StringBuilder(2 * (end - begin));
        for(int i=begin ;i < end; i++) {
            hexBuilder.append(Character.forDigit((bytes[i] & 0xF0) >> 4, 16)); // 转化高四位
            hexBuilder.append(Character.forDigit((bytes[i] & 0x0F), 16)); // 转化低四位
            hexBuilder.append(" ");
        }
        String str=hexBuilder.toString().toUpperCase();
        return str;

    }

    @Override
    public String call() throws Exception{
        try {
            // 读取客户端数据并存储
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            byte[] bytes = new byte[1024]; // 假设发送的字节数不超过 1024 个
            int size = dis.read(bytes); // size 是读取到的字节数
            hex = bytes(bytes, 0, size);

//            sensorInsert(sensor_type,hex);
            dis.close();
        } catch (Exception e) {
            System.out.println("服务器 run 异常: " + e.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    socket = null;
                    System.out.println("服务端 finally 异常:" + e.getMessage());
                }
            }
        }
        return hex;
    }
}
