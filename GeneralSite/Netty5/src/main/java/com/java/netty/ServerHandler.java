package com.java.netty;

import java.io.UnsupportedEncodingException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

public class ServerHandler extends ChannelHandlerAdapter  {
	/*
	 * 这里我们覆盖了chanelRead()事件处理方法。 每当从客户端收到新的数据时， 这个方法会在收到消息时被调用，
     * 这个例子中，收到的消息的类型是ByteBuf
     * 
     * @param ctx
     *            通道处理的上下文信息
     * @param msg
     *            接收的消息
     */
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {

    	ByteBuf buf = (ByteBuf) msg;
    	byte[] req = new byte[buf.readableBytes()];
    	buf.readBytes(req);
    	String body = new String (req,"utf-8");
    	System.out.println("server:"+body);
    	//服务器端给客户端的响应
    	String response ="Hi Client";
    	ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
    }

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		//服务器端给客户端的响应
    	String response ="Hi Client";
    	ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
	}


}
