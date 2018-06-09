package com.java.Netty时间服务器TCP粘包;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeServerHandler extends ChannelHandlerAdapter {
	 private int counter = 0;

	    @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	        String body = (String) msg;
	        // counter的作用是标记这是第几次收到客户端的请求
	        System.out.println("The time server receive order : " + body + " ; the counter is : " + ++counter);
	        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? 
	                new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
	        currentTime = currentTime + System.getProperty("line.separator");
	        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
	        ctx.write(resp);
	    }

	    @Override
	    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
	        ctx.flush();
	    }

	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	        ctx.close();
	    }

}
