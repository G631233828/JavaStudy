package com.java.TCP拆包粘包;

import java.net.SocketAddress;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;

//用于读取客户端发来的信息
public class ClientHandler implements ChannelHandler {

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub

	}
	
	//客户端与服务器连接成功后
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// 发送SmartCar协议的消息  
        // 要发送的信息  
        String data = "I am client ...";  
        // 获得要发送信息的字节数组  
        byte[] content = data.getBytes();  
        // 要发送信息的长度  
        int contentLength = content.length;  
  
        SmartCarProtocol protocol = new SmartCarProtocol(contentLength, content);  
  
        ctx.writeAndFlush(protocol);  

	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub

	}
	 // 只是读数据，没有写数据的话  
    // 需要自己手动的释放的消息 
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		  try {  
	            // 用于获取客户端发来的数据信息  
	            SmartCarProtocol body = (SmartCarProtocol) msg;  
	            System.out.println("Client接受的客户端的信息 :" + body.toString());  
	  
	        } finally {  
	            ReferenceCountUtil.release(msg);  
	        }  

	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress,
			ChannelPromise promise) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void read(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void flush(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub

	}

}
