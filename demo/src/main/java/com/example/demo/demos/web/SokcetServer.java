package com.example.demo.demos.web;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SokcetServer implements Runnable{
    public static void main(String[] args) throws IOException {
        // 创建服务端ServerSocket
        ServerSocket server = new ServerSocket();
        // 绑定在某一个端口上
        server.bind(new InetSocketAddress(8888));// 只有端口，默认绑定本机的IP -- localhost
        // 监听端口（堵塞，等待他人连接）
        // 获取收到的资源
        int count = 0;
        while (true){
            Socket socket = server.accept();

            InputStream inputStream = socket.getInputStream();

            byte[] buf = new byte[1024 * 1024];
            int len;
            while ((len = inputStream.read(buf)) != -1) {
                String command = new String(buf, 0, len);
                System.out.println(command);

                String headCommand = command.split(" ")[0];
                String nextCommand = command.split(" ")[1];
                if(headCommand.equals("load")){

                }else if(headCommand.equals("unload")){

                }else if(headCommand.equals("quit")){

                }

            }
            inputStream.close();
            socket.close();
            if(count > 3)break;
        }
        System.out.println("end!!");
    }

    @SneakyThrows
    @Override
    public void run() {
        // 创建服务端ServerSocket
        ServerSocket server = new ServerSocket();
        // 绑定在某一个端口上
        server.bind(new InetSocketAddress(8888));// 只有端口，默认绑定本机的IP -- localhost
        // 监听端口（堵塞，等待他人连接）
        // 获取收到的资源
        int count = 0;
        while (true){
            Socket socket = server.accept();

            InputStream inputStream = socket.getInputStream();

            byte[] buf = new byte[1024 * 1024];
            int len;
            while ((len = inputStream.read(buf)) != -1) {
                String command = new String(buf, 0, len);
                System.out.println(command);

                String headCommand = command.split(" ")[0];
                String nextCommand = command.split(" ")[1];
                if(headCommand.equals("load")){

                }else if(headCommand.equals("unload")){

                }else if(headCommand.equals("quit")){

                }

            }
            inputStream.close();
            socket.close();
            if(count > 3)break;
        }
        System.out.println("end!!");
    }
}
