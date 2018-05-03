package com.java.丢弃服务;

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
		
		int port = 7777;
		
		EventLoopGroup sGroup = new NioEventLoopGroup();
		EventLoopGroup cGroup = new NioEventLoopGroup();
		
		ServerBootstrap sb = new ServerBootstrap();
		
		
		sb.group(sGroup, cGroup)
		.channel(NioServerSocketChannel.class)
		.option(ChannelOption.SO_BACKLOG,1024)//tcp缓冲区大小
		.option(ChannelOption.SO_SNDBUF,32*1024)
		.option(ChannelOption.SO_RCVBUF, 32*1024)
		.option(ChannelOption.SO_KEEPALIVE, true)
		.childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new DiscardServerHandler());
				
			}
		})
		;
		
		ChannelFuture cf = sb.bind(port).sync();
		cf.channel().closeFuture().sync();
		sGroup.shutdownGracefully();
		cGroup.shutdownGracefully();
		
		
		
	}
	
	
}
