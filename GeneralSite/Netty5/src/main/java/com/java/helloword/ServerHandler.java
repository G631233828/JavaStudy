package com.java.helloword;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerHandler extends ChannelHandlerAdapter {
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String response = simple.format(new Date())+":success receive a message";
		ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			ByteBuf  buf = (ByteBuf) msg;
			//创建大小为buf可读空间大小的字节数组
			byte[] req = new byte[buf.readableBytes()];
			buf.readBytes(req);
			String body = new String(req,"UTF-8");
			System.out.println("client:"+body);
			//服务器端的响应
			String response = simple.format(new Date())+":success receive a message";
			ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
	}
}
