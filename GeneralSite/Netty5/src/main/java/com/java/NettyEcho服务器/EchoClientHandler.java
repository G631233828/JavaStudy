package com.java.NettyEcho·þÎñÆ÷;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class EchoClientHandler extends ChannelHandlerAdapter {

	private int counter;
	
	private static final String ECHO_REQ="HI, FLIAY . WELCOME TO NETTY5 $_";
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Client-ChannelActive");
		for(int i = 0 ;i<10;i++){
			ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
		}

		
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	
		String body = (String) msg;
		
		System.out.println("This is "+ ++counter+"times receiver server:["+body+"]");
		
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	
	
	
	
}
