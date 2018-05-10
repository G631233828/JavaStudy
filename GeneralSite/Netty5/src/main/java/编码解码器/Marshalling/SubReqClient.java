package ±àÂë½âÂëÆ÷.Marshalling;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class SubReqClient {

	public void connect(int port, String host) {
		EventLoopGroup group = new NioEventLoopGroup();

		Bootstrap b = new Bootstrap();
		try {
			b.group(group).channel(NioSocketChannel.class)
			.option(ChannelOption.SO_KEEPALIVE, false)
					.handler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
							ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
							ch.pipeline().addLast(new SubReqClientHandler());
						}
					});
			ChannelFuture f = b.connect(host, port).sync();
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			group.shutdownGracefully();
		}

	}

	public static void main(String[] args) {

			
			new SubReqClient().connect(8888, "127.0.0.1");
		
		
		
		
	}
	
}
