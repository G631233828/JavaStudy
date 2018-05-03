package com.java.Netty时间服务器;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {

	public void connect(String host, int port) {
		EventLoopGroup cGroup = new NioEventLoopGroup();
		try {

			Bootstrap b = new Bootstrap();

			b.group(cGroup)
			.channel(NioSocketChannel.class)
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new TimeClientHandler());

				}
			});

			// 发起异步连接操作
			ChannelFuture f = b.connect(host, port).sync();
			
			
			//f.channel().writeAndFlush(Unpooled.copiedBuffer("QUERY TIME ORDER".getBytes()));
//			Thread.sleep(1000);
//			
			
			f.channel().closeFuture().sync();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 优雅退出
			cGroup.shutdownGracefully();
		}

	}

	public static void main(String[] args) {
		int port = 8888;
		String host = "127.0.0.1";

		new TimeClient().connect(host, port);

	}

}
