package com.java.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTest {

}

class send {

	public static void main(String[] args) {

		try {
			Socket socket = new Socket("localhost", 1000);

			OutputStream out = socket.getOutputStream();

			byte[] buf = "你好Socket！新年好！".getBytes();

			out.write(buf);
			
			
			InputStream in = socket.getInputStream();
			
			byte[] b = new byte[1024];
			
			int len = in.read(b);
			
			System.out.println(new String(b,0,len));

			out.close();
			in.close();
			socket.close();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class server {

	public static void main(String[] args) {

		try {
			ServerSocket ss = new ServerSocket(1000);

			Socket s = ss.accept();

			InputStream in = s.getInputStream();

			byte[] buf = new byte[1024];

			int len = in.read(buf);

			String msg = new String(buf, 0, len);

			System.out.println(s.getInetAddress().getHostAddress() + ":" + msg);
			
			//服务器反馈
			
			byte[] b = "服务器反馈：你也新年好！".getBytes();
			
			OutputStream out = s.getOutputStream();
			
			out.write(b);
			
			out.close();
			in.close();
			s.close();
			ss.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}