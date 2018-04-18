package com.java.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {

	
	/**
	 * 
	 * @param args
	 * 对于channelOption.SO_BACKLOG的解释：
	 * 服务器端TCP内核模块维护有两个队列，我们称为A,B
	 * 客户端想服务器端connect的时候，会发送带有SYN标志的包（第一次握手）
	 * 服务器收到客户端发来的SYN时，想客户端发送SYN ACK确认（第二次握手）
	 * 此时TCP内核模块把客户端连接从A队列移到B队列，连接完成，应用程序的accept会返回，
	 * 也就是说accept从B队列中去除完成三次握手的连接
	 * A队列和B队列的长度之和是backlog，当A，B队列的长度之和大于backlog时，新连接将会被TCP内核拒绝，
	 * 所以，如果backlog过小，可能会出现accept速度跟不上， A,B队列满了，导致新的客户端无法连接。
	 * 要注意的是：backlog对程序支持的连接数，backlog影响的只是还没有accept取出的连接。
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		//用户处理服务器端接收客户端连接的
		EventLoopGroup pGroup = new NioEventLoopGroup();
		//用于进行网络通信的（网络读写）
		EventLoopGroup cGroup = new NioEventLoopGroup();
		//创建辅助工具类，用于服务器通道的一系列配置
		ServerBootstrap b = new ServerBootstrap();
		b.group(pGroup, cGroup)//绑定两个线程组
		.channel(NioServerSocketChannel.class)//指定NIO的模式
		.option(ChannelOption.SO_BACKLOG, 1024)//设置tcp缓冲区的大小
		.option(ChannelOption.SO_SNDBUF, 32*1024)//设置发送缓冲区大小
		.option(ChannelOption.SO_RCVBUF, 30*1024)//设置接收缓冲区大小
		.option(ChannelOption.SO_KEEPALIVE, true)//保持连接
		.childHandler(new ChannelInitializer<SocketChannel>(){

			@Override
			protected void initChannel(SocketChannel sc) throws Exception {

				sc.pipeline().addLast(new ServerHandler());//在这里配置具体数据接收方法的处理
			}
			
		});
		
		ChannelFuture cf1 = b.bind(5200).sync();//进行版定
		
		cf1.channel().closeFuture().sync();//等待关闭
		
		pGroup.shutdownGracefully();
		cGroup.shutdownGracefully();
		
	}
	
}
