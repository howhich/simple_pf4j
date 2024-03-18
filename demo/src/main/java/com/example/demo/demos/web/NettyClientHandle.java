package com.example.demo.demos.web;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;


public class NettyClientHandle extends ChannelInboundHandlerAdapter {

        //如果client 端服务启动完成后
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {

            System.out.println("client: "+ctx+"启动完成");
//            ctx.writeAndFlush(Unpooled.copiedBuffer("hello,netty server...", CharsetUtil.UTF_8));
            Scanner scanner = new Scanner(System.in);
            int flag = 1;
            while (scanner.hasNextLine() && flag==1){
                String msg = scanner.nextLine();
                if(msg.equals("quit")){
//                    ctx.writeAndFlush(msg);
                    System.out.println("end of current client");
                    flag = 0;
                }
                ctx.writeAndFlush(Unpooled.copiedBuffer(msg,CharsetUtil.UTF_8));
            }

        }

        //当通道有读事件时
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

            ByteBuf byteBuf = (ByteBuf) msg;
//            System.out.println(((ByteBuf) msg).toString(CharsetUtil.UTF_8));
            System.out.println("服务器端回复消息："+byteBuf.toString(CharsetUtil.UTF_8));
            System.out.println("服务器端地址是："+ctx.channel().remoteAddress());
        }

        //当通道有异常时

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
    }

