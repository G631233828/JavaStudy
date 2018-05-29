package com.java.helloword;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {

	public static void main(String[] args) throws InterruptedException {

		int port = 3000;
		// �������ڷ������˽��տͻ������ӵ�
		EventLoopGroup sGroup = new NioEventLoopGroup();
		// �������ڽ�������ͨ�ŵ�ͨ��
		EventLoopGroup cGroup = new NioEventLoopGroup();
		// �������������࣬���ڷ�����ͨ����һϵ������
		ServerBootstrap b = new ServerBootstrap();
		// ���ȴ���Ҫ���߳�����а�
		b.group(sGroup, cGroup)
				// �󶨳ɹ�֮����Ҫָ��NIO��ģʽ
				.channel(NioServerSocketChannel.class)
				// ����tcp��������С
				.option(ChannelOption.SO_BACKLOG, 1024)
				// ���÷��ͻ�������С��buffer��������С��
				.option(ChannelOption.SO_SNDBUF, 32 * 1024)
				// ���ý��ջ�������С
				.option(ChannelOption.SO_RCVBUF, 32 * 1024)
				//��������
				.option(ChannelOption.SO_KEEPALIVE,true)
				// ��ȡ�ͻ�������ͨ��
				.childHandler(new ChannelInitializer<SocketChannel>() {
					
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						// ���ڹ������ݣ����룬���룬���������þ�������ݽ��շ����ʹ�������
						ch.pipeline().addLast(new ServerHandler());
					}
				});
		// ���а��첽ͨ��
		ChannelFuture cf = b.bind(port).sync();

		cf.channel().closeFuture().sync();
		cGroup.shutdownGracefully();
		sGroup.shutdownGracefully();

	}

}