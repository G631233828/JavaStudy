package com.java.TCP拆包粘包;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {

	public void connect (int port,String host) throws InterruptedException{
		//配置客户端的NIO线程组
		EventLoopGroup group = new NioEventLoopGroup();
		try{
			//客户端辅助启动类，对客户端配置
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new MyChannelHandler());
			//异步链接服务器 同步等待链接成功
			ChannelFuture f =b.connect(host,port).sync();
			
			
			f.channel().writeAndFlush(Unpooled.copiedBuffer("hello netty!!".getBytes()));
			
			//等待链接关闭
			f.channel().closeFuture().sync();
			
		}finally{
			group.shutdownGracefully();
			System.out.println("客户端释放了现场资源");
		}
		
		
	}
	
	
	private class MyChannelHandler extends ChannelInitializer<SocketChannel>{

		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			//添加自定义协议的解码工具
			ch.pipeline().addLast(new SmartCarEncoder());
			ch.pipeline().addLast(new SmartCarDecoder());
			//处理网络IO
			ch.pipeline().addLast(new ClientHandler());
			
			
		}
		
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		new Client().connect(8000, "127.0.0.1");
	}
	
	
	
	
	
	
	

}
