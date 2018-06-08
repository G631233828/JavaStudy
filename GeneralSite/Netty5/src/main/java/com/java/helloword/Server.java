package com.java.helloword;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {
	public static void main(String[] args) throws InterruptedException {
		int port = 3000;
		// 创建用于服务器端接收客户端连接的
		EventLoopGroup sGroup = new NioEventLoopGroup();
		// 创建用于进行网络通信的通道
		EventLoopGroup cGroup = new NioEventLoopGroup();
		// 创建辅助工具类，用于服务器通道的一系列配置
		ServerBootstrap b = new ServerBootstrap();
		// 首先创建要与线程组进行绑定
		b.group(sGroup, cGroup)
				// 绑定成功之后需要指定NIO的模式
				.channel(NioServerSocketChannel.class)
				// 设置tcp缓冲区大小
				.option(ChannelOption.SO_BACKLOG, 1024)
				// 设置发送缓冲区大小（buffer缓冲区大小）
				.option(ChannelOption.SO_SNDBUF, 32 * 1024)
				// 设置接收缓冲区大小
				.option(ChannelOption.SO_RCVBUF, 32 * 1024)
				//保持连接
				.option(ChannelOption.SO_KEEPALIVE,true)
				// 获取客户端连接通道
				.childHandler(new ChannelInitializer<SocketChannel>() {
					
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						// 用于过滤数据，编码，解码，在这里配置具体的数据接收方法和处理方法
						ch.pipeline().addLast(new ServerHandler());
					}
				});
		// 进行绑定异步通道
		ChannelFuture cf = b.bind(port).sync();
		cf.channel().closeFuture().sync();
		cGroup.shutdownGracefully();
		sGroup.shutdownGracefully();
	}
}
