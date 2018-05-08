package com.java.NettyEcho服务器;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class EchoServer {

	
	static int port =9999;
	
	
	public void bind(int port){
		EventLoopGroup cGroup = new NioEventLoopGroup();
		EventLoopGroup sGroup = new NioEventLoopGroup();
		
		ServerBootstrap s = new ServerBootstrap();
		try{
		s.group(cGroup, sGroup)
		.channel(NioServerSocketChannel.class)
		.option(ChannelOption.SO_BACKLOG, 100)
		.handler(new LoggingHandler(LogLevel.INFO))
		.childHandler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
		/*		ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
				ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));*/
				//FixedLengthFrameDecoder固定长度解码器，能够按照指定的长度对消息进行自动解码
				ch.pipeline().addLast(new FixedLengthFrameDecoder(20));
				
				ch.pipeline().addLast(new StringDecoder());
				ch.pipeline().addLast(new EchoServerHandler());
				
			}
		}) ;
		
		//绑定端口，同步等待成功
		ChannelFuture f = s.bind(port).sync();
		//等待服务器监听端口关闭
		f.channel().closeFuture().sync();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			cGroup.shutdownGracefully();
			sGroup.shutdownGracefully();
		}
	}
	
	
	
	
	public static void main(String[] args) {
		EchoServer e = new EchoServer();
		e.bind(port);
	}
	
	
	
	
}
