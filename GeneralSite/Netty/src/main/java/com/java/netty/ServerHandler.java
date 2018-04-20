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
	 * �������Ǹ�����chanelRead()�¼����������� ÿ���ӿͻ����յ��µ�����ʱ�� ������������յ���Ϣʱ�����ã�
     * ��������У��յ�����Ϣ��������ByteBuf
     * 
     * @param ctx
     *            ͨ����������������Ϣ
     * @param msg
     *            ���յ���Ϣ
     */
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {

    	ByteBuf buf = (ByteBuf) msg;
    	byte[] req = new byte[buf.readableBytes()];
    	buf.readBytes(req);
    	String body = new String (req,"utf-8");
    	System.out.println("server:"+body);
    	//�������˸��ͻ��˵���Ӧ
    	String response ="Hi Client";
    	ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
    }

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		//�������˸��ͻ��˵���Ӧ
    	String response ="Hi Client";
    	ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.handlerRemoved(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}

	

}