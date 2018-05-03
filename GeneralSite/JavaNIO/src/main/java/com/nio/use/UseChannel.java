package com.nio.use;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class UseChannel {


	public static void main(String[] args) {
		
		try {
			//1.打开ServerSocketChannel 用户监听客户端的连接
			ServerSocketChannel sc =  ServerSocketChannel.open();
			//2.绑定监听端口，设置连接位非阻塞模式
			sc.socket().bind(new InetSocketAddress(InetAddress.getByName("IP"), 8181));
			sc.configureBlocking(false);//设置阻塞模式为false
			//3.创建Reactor线程，创建多路复用器并启动线程
			Selector selector = Selector.open();
			new Thread(new ReactorTack()).start();
			//4.将ServerSocketChannel 注册到Reactor线程的多路复用器Selector上，监听ACCEPT事件
			SelectionKey key = sc.register(selector, SelectionKey.OP_ACCEPT);
			//5.嘟噜复用器在线程run方法的无限循环体内轮询准备就绪的key
			int num =selector.select();
			Set selectedKeys = selector.selectedKeys();
			Iterator it = selectedKeys.iterator();
			while(it.hasNext()){
				SelectionKey key1 = (SelectionKey) it.next();
			}
			//6.多路复用器监听到有新的客户端接入，处理新的接入请求，完成TCP三次握手，建立物理链路
			SocketChannel channel = sc.accept();
			//7.设置客户端链路为非阻塞模式
			channel.configureBlocking(false);
			channel.socket().setReuseAddress(true);
			//8.将新接入的客户端连接注册到Reactor线程的多路复用器上，监听读操作，读取客户端发送的网络消息
			SelectionKey ck = channel.register(selector, SelectionKey.OP_READ);
			//9.异步读取客户端请求消息到缓冲区
			ByteBuffer b = null;
			int readNumber = channel.read(b);
			//10.对ByteBuffer进行编码解码，如果有半包消息指针reset，继续读取后续的报文，将解码成功的消息封装成Task，投递到业务线程池中，进行业务逻辑编排
			//11.将pojo对象encode成ByteBuffer，调用SocketChannel的异步write接口将消息异步发送给客户端
			channel.write(b);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
