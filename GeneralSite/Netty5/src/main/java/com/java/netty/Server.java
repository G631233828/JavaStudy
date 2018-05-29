package com.java.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
/**
 * 
 * @author fliay
 *
 */
public class Server {

	
	/**
	 * 
	 * @param args
	 * ����channelOption.SO_BACKLOG�Ľ��ͣ�
	 * ��������TCP�ں�ģ��ά�����������У����ǳ�ΪA,B
	 * �ͻ������������connect��ʱ�򣬻ᷢ�ʹ���SYN��־�İ�����һ�����֣�
	 * �������յ��ͻ��˷�����SYNʱ����ͻ��˷���SYN ACKȷ�ϣ��ڶ������֣�
	 * ��ʱTCP�ں�ģ��ѿͻ������Ӵ�A�����Ƶ�B���У�������ɣ�Ӧ�ó����accept�᷵�أ�
	 * Ҳ����˵accept��B������ȥ������������ֵ�����
	 * A���к�B���еĳ���֮����backlog����A��B���еĳ���֮�ʹ���backlogʱ�������ӽ��ᱻTCP�ں˾ܾ���
	 * ���ԣ����backlog��С�����ܻ����accept�ٶȸ����ϣ� A,B�������ˣ������µĿͻ����޷����ӡ�
	 * Ҫע����ǣ�backlog�Գ���֧�ֵ���������backlogӰ���ֻ�ǻ�û��acceptȡ�������ӡ�
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		//�û������������˽��տͻ������ӵ�
		EventLoopGroup pGroup = new NioEventLoopGroup();
		//���ڽ�������ͨ�ŵģ������д��
		EventLoopGroup cGroup = new NioEventLoopGroup();
		//�������������࣬���ڷ�����ͨ����һϵ������
		ServerBootstrap b = new ServerBootstrap();
		b.group(pGroup, cGroup)//�������߳���
		.channel(NioServerSocketChannel.class)//ָ��NIO��ģʽ
		.option(ChannelOption.SO_BACKLOG, 1024)//����tcp�������Ĵ�С
		.option(ChannelOption.SO_SNDBUF, 32*1024)//���÷��ͻ�������С buffer��������С
		.option(ChannelOption.SO_RCVBUF, 30*1024)//���ý��ջ�������С
		.option(ChannelOption.SO_KEEPALIVE, true)//��������
		.childHandler(new ChannelInitializer<SocketChannel>(){//��ȡ�ͻ�������ͨ��

			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				//�������ݣ����룬����
				//���������þ������ݽ��շ����Ĵ���
				sc.pipeline().addLast(new ServerHandler());
			}
			
		});
		
		ChannelFuture cf1 = b.bind(5200).sync();//���а󶨣��첽ͨ����
		
		cf1.channel().closeFuture().sync();//�ȴ��ر�
		
		pGroup.shutdownGracefully();
		cGroup.shutdownGracefully();
		
	}
	
}