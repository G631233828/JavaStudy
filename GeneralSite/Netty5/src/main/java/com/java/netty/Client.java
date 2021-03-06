package com.java.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 
 * @author fliay
 *
 */
public class Client {
	
	
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup();//定义一个客户端的接收通道
		Bootstrap b = new Bootstrap();
		
		b.group(group).channel(NioSocketChannel.class)
		.handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {

				ch.pipeline().addLast(new ClientHandler());
			}
		});
		
		ChannelFuture cf1 = b.connect("127.0.0.1",5200);
		
		cf1.channel().writeAndFlush(Unpooled.copiedBuffer("hello netty!!".getBytes()));
		Thread.sleep(1000);
		cf1.channel().writeAndFlush(Unpooled.copiedBuffer("hello netty!!".getBytes()));
		Thread.sleep(1000);
		cf1.channel().writeAndFlush(Unpooled.copiedBuffer("hello netty!!".getBytes()));
		
		cf1.channel().closeFuture().sync();
		group.shutdownGracefully();
	}
	
	
	
}
