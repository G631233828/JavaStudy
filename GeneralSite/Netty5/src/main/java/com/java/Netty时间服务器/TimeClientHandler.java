package com.java.Netty时间服务器;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler extends ChannelHandlerAdapter {

	// 改造
	private int counter;
	private byte[] req;

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		ByteBuf buf = (ByteBuf) msg;
		req = new byte[buf.readableBytes()];
		buf.readBytes(req);

		String body = new String(req, "UTF-8");

		// System.out.println("Now is:" + body);
		System.out.println("Now is:" + body + ";the counter is:" + ++counter);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		/*
		 * ByteBuf message = null; for (int i = 0; i < 100; i++) { message =
		 * Unpooled.copiedBuffer(req); message.writeBytes(req);
		 * ctx.writeAndFlush(message); }
		 */for (int i = 0; i < 100; i++) {
			ctx.writeAndFlush(Unpooled.copiedBuffer("QUERY TIME ORDER".getBytes()));
		}
	}

}
