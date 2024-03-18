package com.example.demo.demos.web;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;
import org.apache.catalina.core.ApplicationContext;
import org.pf4j.JarPluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;

@Component
public class NettyServerHandle extends ChannelInboundHandlerAdapter {
    @Autowired
    public static JarPluginManager jarPluginManager;
//    @Bean
//    public JarPluginManager jarPluginManager(){
//        return jarPluginManager();
//    }
    @RequestMapping("/sas")
    public void test(){

}
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        jarPluginManager = new JarPluginManager();
    }

    /**
     * 读取数据
     *
     * @param: 1.ChannelHandlerContext ctx:上下文对象, 含有 管道 pipeline , 通道 channel, 地址
     * @param: 2. Object msg: 就是客户端发送的数据 默认 Object
     */
    @Override
    @RequestMapping("/sad")
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("服务器读取线程 " + Thread.currentThread().getName());
//        System.out.println("server ctx =" + ctx);
//        System.out.println("看看 channel 和 pipeline 的关系");

        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline(); //本质是一个双向链接, 出站入站

        //将 msg 转成一个 ByteBuf,ByteBuf 是 Netty 提供的，不是 NIO 的 ByteBuffer.
        ByteBuf buf = (ByteBuf) msg;
//        System.out.println("直接捕获的消息是"+msg);
        System.out.println("客户端发送消息是:" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址:" + channel.remoteAddress());
        String tureMsg = buf.toString(CharsetUtil.UTF_8);
        String [] strings = tureMsg.split(" ");
        if(strings.length!=2){
            ctx.writeAndFlush("Your Input Has Error!");
//            ctx.writeAndFlush(Unpooled.copiedBuffer(msg,CharsetUtil.UTF_8));

        }
        String headMsg = strings[0];
        String tailMsg = strings[1];
        try {
            if (headMsg.equals("load")) {
                //ByTXTFile
                byte[] bytes = tailMsg.getBytes();
                FileOutputStream fileOutputStream = new FileOutputStream(new File("F:\\fuchuang\\pf4jTset\\demo\\src\\main\\java\\com\\example\\demo\\plugin.txt"));
                fileOutputStream.write(bytes);
                Plugin.startPlugin(tailMsg, jarPluginManager);
                System.out.println("插件" + tailMsg + "已经启用");

            } else if (headMsg.equals("unload")) {
//                Plugin.stopPlugins(tailMsg, jarPluginManager);
                System.out.println("插件" + tailMsg + "已经停用");
            }
        }catch (Exception e){
            System.out.println(e);
        }


    }

    /**
     * 读取数据完成后
     *
     * @param:
     * @return:
     * @auther:
     * @date:
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //writeAndFlush 是 write + flush
        //将数据写入到缓存，并刷新
        //一般讲，我们对这个发送的数据进行编码
//        ctx.writeAndFlush(Unpooled.copiedBuffer("已收到客户端消息", CharsetUtil.UTF_8));
//        Thread.sleep(5000);
//        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端2", CharsetUtil.UTF_8));

    }

    //处理异常, 一般是需要关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}