package com.java.Netty时间服务器TCP粘包;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class TimeServer {

	

	public void bind(int port) {

		// 配置服务器的NIO线程组
		EventLoopGroup sGroup = new NioEventLoopGroup();
		EventLoopGroup cGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap s = new ServerBootstrap();

			s.group(sGroup, cGroup).channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 1024)
				.option(ChannelOption.SO_KEEPALIVE, true)
				.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							//
							//ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
							ch.pipeline().addLast(new StringDecoder());
							ch.pipeline().addLast(new TimeServerHandler());
						}
					});

			// 绑定端口，同步等待成功
			ChannelFuture f = s.bind(port).sync();

			// 等待服务端监听端口关闭
			f.channel().closeFuture().sync();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 优雅的退出，释放所有线程资源
			sGroup.shutdownGracefully();
			cGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		int port = 8888;
		// if(args!=null&&args.length>0){
		// try{
		// port = Integer.valueOf(args[0]);
		// }catch(NumberFormatException e){
		// e.printStackTrace();
		// }
		// }
		//
		new TimeServer().bind(port);
	}

}
