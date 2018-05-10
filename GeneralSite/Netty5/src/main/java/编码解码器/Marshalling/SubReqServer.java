package ±àÂë½âÂëÆ÷.Marshalling;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class SubReqServer {

	public void bind(int port) {
		EventLoopGroup cGroup = new NioEventLoopGroup();
		EventLoopGroup sGroup = new NioEventLoopGroup();

		try {

			ServerBootstrap b = new ServerBootstrap();
			b.group(cGroup, sGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024)
//			.option(ChannelOption.SO_KEEPALIVE, true)
					.handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {

							ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
							ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
							ch.pipeline().addLast(new SubReqServerHandler());
						}
					});
			ChannelFuture f = b.bind(port).sync();
			f.channel().closeFuture().sync();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cGroup.shutdownGracefully();
			sGroup.shutdownGracefully();
		}

	}

	public static void main(String[] args) {
		int port = 8888;
		new SubReqServer().bind(port);

	}

}
