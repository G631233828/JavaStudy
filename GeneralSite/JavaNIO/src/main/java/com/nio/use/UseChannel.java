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
			//1.��ServerSocketChannel �û������ͻ��˵�����
			ServerSocketChannel sc =  ServerSocketChannel.open();
			//2.�󶨼����˿ڣ���������λ������ģʽ
			sc.socket().bind(new InetSocketAddress(InetAddress.getByName("IP"), 8181));
			sc.configureBlocking(false);//��������ģʽΪfalse
			//3.����Reactor�̣߳�������·�������������߳�
			Selector selector = Selector.open();
			new Thread(new ReactorTack()).start();
			//4.��ServerSocketChannel ע�ᵽReactor�̵߳Ķ�·������Selector�ϣ�����ACCEPT�¼�
			SelectionKey key = sc.register(selector, SelectionKey.OP_ACCEPT);
			//5.��ิ�������߳�run����������ѭ��������ѯ׼��������key
			int num =selector.select();
			Set selectedKeys = selector.selectedKeys();
			Iterator it = selectedKeys.iterator();
			while(it.hasNext()){
				SelectionKey key1 = (SelectionKey) it.next();
			}
			//6.��·���������������µĿͻ��˽��룬�����µĽ����������TCP�������֣�����������·
			SocketChannel channel = sc.accept();
			//7.���ÿͻ�����·Ϊ������ģʽ
			channel.configureBlocking(false);
			channel.socket().setReuseAddress(true);
			//8.���½���Ŀͻ�������ע�ᵽReactor�̵߳Ķ�·�������ϣ���������������ȡ�ͻ��˷��͵�������Ϣ
			SelectionKey ck = channel.register(selector, SelectionKey.OP_READ);
			//9.�첽��ȡ�ͻ���������Ϣ��������
			ByteBuffer b = null;
			int readNumber = channel.read(b);
			//10.��ByteBuffer���б�����룬����а����Ϣָ��reset��������ȡ�����ı��ģ�������ɹ�����Ϣ��װ��Task��Ͷ�ݵ�ҵ���̳߳��У�����ҵ���߼�����
			//11.��pojo����encode��ByteBuffer������SocketChannel���첽write�ӿڽ���Ϣ�첽���͸��ͻ���
			channel.write(b);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}