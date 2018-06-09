package com.java.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Random;
import java.util.Scanner;

public class 面向无连接UDP {

}

/**
 * 
 * @author fliay 创建udp发送
 */
class UDPSend {

	public static void main(String[] args) {

		try {
			
			
			DatagramSocket ds = new DatagramSocket();
			while(true){
				
				int num = (int) (Math.random()*200);
				
				 byte[] bt = ("你好"+num).getBytes();
				 
				 //255是广播的所有的设备都会收到
					DatagramPacket dp = new DatagramPacket(bt, 0, bt.length,
							InetAddress.getByName("192.168.1.255"), 7878);
					// 发送
					ds.send(dp);
					Thread.sleep(2000);
			}
			

				//ds.close();

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class UDPReceive {

	public static void main(String[] args) {

		try {

			DatagramSocket ds = new DatagramSocket();

			byte[] buf = new byte[1024];

			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			while(true){
				ds.receive(dp);

				String ip = dp.getAddress().getHostAddress();
				int port = dp.getPort();
				String data = new String(dp.getData());

				System.out.println("发送者Ip:" + ip);
				System.out.println("发送者端口号:" + port);
				System.out.println("接收内容:" + data);
			}
			

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
