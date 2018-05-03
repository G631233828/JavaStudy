package com.java.Netty时间服务器TCP粘包;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler extends ChannelHandlerAdapter {

	// 改造
	   private int counter;

	    private byte[] req;

	    public TimeClientHandler() {
	        req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
	    }

	    @Override
	    public void channelActive(ChannelHandlerContext ctx) {
	        ByteBuf message = null;
	        for(int i = 0; i < 100; i++) {
	            message = Unpooled.buffer(req.length);
	            message.writeBytes(req);
	            ctx.writeAndFlush(message);
	        }
	    }

	    @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	        String body = (String) msg;
	        // counter的作用是标记这是第几次收到客户端的请求
	        System.out.println("Now is : " + body + " ; the counter is : " + ++counter);
	    }

	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
	        ctx.close();
	    }

}
