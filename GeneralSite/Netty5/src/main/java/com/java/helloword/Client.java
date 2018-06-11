package com.java.helloword;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
	public static void main(String[] args) throws InterruptedException {
		int port =3000;
		String host="127.0.0.1";
		EventLoopGroup cGroup = new NioEventLoopGroup();		
		Bootstrap b = new Bootstrap();		
		b.group(cGroup)
		//.option(ChannelOption.SO_TIMEOUT, 20000)超时
		.channel(NioSocketChannel.class)
		.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new ClientHandler());			
			}
		});		
		ChannelFuture cf = b.connect(host,port);
			cf.channel().writeAndFlush(Unpooled.copiedBuffer("An message from client".getBytes()));
			Thread.sleep(1000);
		cf.channel().closeFuture().sync();
		cGroup.shutdownGracefully();		
	}
}
