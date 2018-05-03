package com.java.Netty时间服务器;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeServerHandler extends ChannelHandlerAdapter {

	// TimerServer改造
	private int counter;

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// // TODO Auto-generated method stub
		// super.handlerAdded(ctx);
		// SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd
		// hh:mm:ss");
		// String response = simple.format(new Date())+":success receive a
		// message";
		//
		// ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);

		// String body = new String(req, "UTF-8");
		//改造
		String body = new String(req, "UTF-8").substring(0, req.length - System.getProperty("line.separator").length());

		//System.out.println("the time server receive order：" + body);
		//改造
		System.out.println("the time server receive order：" + body+";the couter is :"+ ++counter);
		
		
		
		
		String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString()
				: "BAD ORDER";

		ctx.writeAndFlush(Unpooled.copiedBuffer(currentTime.getBytes()));

	}

}
