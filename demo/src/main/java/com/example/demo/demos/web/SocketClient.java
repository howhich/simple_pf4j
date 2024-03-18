package com.example.demo.demos.web;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketClient implements Runnable {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        // 使用socket进行连接（套接字：IP + 端口号），三次握手底层已帮我们实现
        socket.connect(new InetSocketAddress(InetAddress.getLocalHost(), 8888));
        // 发送消息
        OutputStream outputStream = socket.getOutputStream();
//        outputStream.write("hello server!".getBytes());

        //输入
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String next = scanner.nextLine();
            outputStream.write(next.getBytes());
        }

        outputStream.close();
        socket.close();
        System.out.println("sent already");
    }

    @Override
    @SneakyThrows
    public void run() {
        Socket socket = new Socket();
        // 使用socket进行连接（套接字：IP + 端口号），三次握手底层已帮我们实现
        socket.connect(new InetSocketAddress(InetAddress.getLocalHost(), 8888));
        // 发送消息
        OutputStream outputStream = socket.getOutputStream();
//        outputStream.write("hello server!".getBytes());

        //输入
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String next = scanner.nextLine();
            outputStream.write(next.getBytes());
        }

        outputStream.close();
        socket.close();
        System.out.println("sent already");
    }
}
