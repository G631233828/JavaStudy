package com.java.丢弃服务;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


/**
 * Netty 丢弃服务
 * @author fliay
 *
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {

	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		
		cause.printStackTrace();
		ctx.close();

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		try{
			
			
		}finally{
			// 直接丢弃
			((ByteBuf) msg).release();
		}
		
		

	}

}
