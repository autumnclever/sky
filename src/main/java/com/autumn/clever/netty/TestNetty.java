package com.autumn.clever.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.Date;

/**
 * @Author: zhangqiuying
 * @Date: 2020/11/23 下午11:43
 */
public class TestNetty {

}

// 服务端
class NettyServer {
    public static void main(String[] args) {
        // bossGroup 表示监听端口，接受新连接线程，主要负责创建新连接
        NioEventLoopGroup boss = new NioEventLoopGroup();
        // workerGroup 表示处理每一条连接的数据读写的线程组，主要用于读取数据以及业务逻辑处理。
        NioEventLoopGroup worker = new NioEventLoopGroup();

        try {
            // 1.创建 ServerBootstrap 实例。
            // 引导类，引导我们进行服务端的启动工作
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    // 2.设置 EventLoopGroup。
                    // 引导类配置两大线程组
                    .group(boss, worker)
                    // 3.设置创建的 Channel 类型。
                    // 指定我们服务端的 IO 模型为 NIO，NioServerSocketChannel 是 Netty 对 NIO 类型的连接的抽象
                    .channel(NioServerSocketChannel.class)
                    // 4.option 配置属性
                    .option(ChannelOption.SO_BACKLOG, 100)
                    // 5.设置 Handler，处理请求
                    .handler(new LoggingHandler(LogLevel.INFO))
                    // 6.设置 ChildHandler，处理对应 channel 的请求
                    // 给这个引导类创建一个 ChannelInitializer，定义后续每条连接的数据读写，业务处理逻辑
                    // 泛型 NioSocketChannel 是 Netty 对 NIO 类型的连接的抽象
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) {
                            // ch.pipeline() 返回的是和这条连接相关的逻辑处理链，采用了责任链模式
                            // addLast() 方法 添加一个逻辑处理器
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                    System.out.println(msg);
                                }
                            });
                        }
                    })
                    // 通过 bind 创建 Channel 并绑定，启动服务
                    .bind(8000);
        } catch (Exception e) {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}

// 客户端
class NettyClient {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            // 1. 创建 Bootstrap 实例
            // 客户端启动的引导类是 Bootstrap
            Bootstrap bootstrap = new Bootstrap();
            // 2. 设置 EventLoop
            // 指定线程模型
            bootstrap.group(group)
                    // 3. 指定 Channel 类型
                    // 指定 IO 模型为 NioSocketChannel
                    .channel(NioSocketChannel.class)
                    // 4. option 配置
                    .option(ChannelOption.TCP_NODELAY, true)
                    // 5. 指定 Handler
                    // 给引导类指定一个 handler，这里主要就是定义连接的业务处理逻辑
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) {
                            ch.pipeline().addLast(new StringEncoder());
                        }
                    });
            // 6.connect
            // connect 方法进行连接，第一个参数可以填写 IP 或者域名，第二个参数填写的是端口号
            // 由于 connect 方法返回的是一个 Future，这个方法是异步的，我们通过 addListener 方法可以监听到连接是否成功
            Channel channel = bootstrap.connect("127.0.0.1", 8000).channel();
            while (true) {
                // writeAndFlush 方法用于发送消息
                channel.writeAndFlush(new Date() + ": hello world!");
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }
}
