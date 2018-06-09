package com.java.TCP拆包粘包;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerHandler extends ChannelHandlerAdapter {
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}

	//用于获取客户端发送的信息
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		//用户获取客户端发来的数据信息
		SmartCarProtocol body =(SmartCarProtocol) msg;
		System.out.println("server接收客户端的信息："+body.toString());
		
		//会写数据给客户端
		String str="Hi I am Server";
		
		SmartCarProtocol response = new SmartCarProtocol(str.getBytes().length, str.getBytes());
		
		//当服务端完成操作和，关闭与客户端的连接
		ctx.writeAndFlush(response);//.addListener(ChannelFutureListener.CLOSE);
		
		//当有写操作时，不需要手动释放msg的作用
		//当只有读操作时，才需要手动释放msg的引用
		
		
	}

	
	
	
	
	
	
	
	
	
}
