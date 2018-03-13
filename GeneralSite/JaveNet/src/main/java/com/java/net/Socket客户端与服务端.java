package com.java.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Socket客户端与服务端 {

	public static void main(String[] args) {

	}

}




class SocketClient {
	
	public static void startClient() throws UnknownHostException, IOException{
		
		//创建socket
		Socket socket = new Socket("192.168.1.171", 8888);
		//发送数据
		OutputStream out = socket.getOutputStream();
		
		byte[] msg = "你好Socket".getBytes();
		
		out.write(msg);
		
		socket.close();
		out.close();
		
	}
	
	public static void main(String[] args) {
		try {
			startClient();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}


class SocketServer{
	
	public static void startServer() throws IOException{
		
		ServerSocket serverSocket = new ServerSocket(8888);
		
		Socket socket =serverSocket.accept();
		
		String ip = socket.getInetAddress().getHostAddress();
		
		InputStream in =socket.getInputStream();
		
		byte[] buf = new byte[1024];
		
		int len = in.read(buf);
		
		String s = new String(buf, 0, len);
		
		System.out.println(ip+":"+s);
		
	}
	
	public static void main(String[] args) {
		try {
			startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}