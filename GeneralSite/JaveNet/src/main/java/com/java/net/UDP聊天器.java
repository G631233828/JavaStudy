package com.java.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDP聊天器 {

	public static void main(String[] args) {
		try {
			DatagramSocket d1 = new DatagramSocket();
			DatagramSocket d2 = new DatagramSocket(8000);
			
			new Thread(new UDPSend2(d1)).start();
			new Thread(new UDPReceive2(d2)).start();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}


class UDPSend2 implements Runnable{
	
	public DatagramSocket ds;
	
	public UDPSend2(DatagramSocket data){
		this.ds=data;
	}

	public void run() {
		
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			String line = null;
			
			try {
				while((line=reader.readLine())!=null){
					if("bye".equals(line)){
						System.out.println("bye");
						return;
					}
					
					byte[] buf = line.getBytes();
					
						DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.1.255"), 8000);
				
						ds.send(dp);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
	
}


class UDPReceive2 implements Runnable {
public DatagramSocket ds;
	
	public UDPReceive2(DatagramSocket data){
		this.ds=data;
	}

	public void run() {
		try {
		while(true){
			
			byte[] buf = new byte[1024];
			
			DatagramPacket dp = new DatagramPacket(buf,buf.length);
			
	
				ds.receive(dp);
			
			String ip = dp.getAddress().getHostAddress();
			String data = new String(dp.getData(),0,dp.getLength());
			
			System.out.println(ip+":"+data);
			
		}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	
}






