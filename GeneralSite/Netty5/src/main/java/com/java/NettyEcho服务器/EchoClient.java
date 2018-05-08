package com.java.NettyEcho服务器;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class EchoClient {

	private static int port = 9999;
	private static String host ="127.0.0.1";

	public static void bind(int port,String host) {

		EventLoopGroup cGroup = new NioEventLoopGroup();

		Bootstrap b = new Bootstrap();

		try {
			b.group(cGroup).channel(NioSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY, true)
			.handler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
				/*			ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
							ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));*/
							
							//FixedLengthFrameDecoder固定长度解码器，能够按照指定的长度对消息进行自动解码
							ch.pipeline().addLast(new FixedLengthFrameDecoder(20));
							ch.pipeline().addLast(new StringDecoder());
							ch.pipeline().addLast(new EchoClientHandler());
						}
					});

			ChannelFuture f = b.connect(host,port).sync();
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		bind(port,host);

	}

}
