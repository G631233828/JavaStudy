package com.java.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	static String address = "127.0.0.1";
	static int port = 8888;

	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;

		try {
			for(int i=0;i<10000;i++){
				socket = new Socket(address, port);
				InputStream ins = socket.getInputStream();
				OutputStream ous = socket.getOutputStream();
				in = new BufferedReader(new InputStreamReader(ins));
				out = new PrintWriter(ous, true);
				out.println("你好服务器"+i);
				String message = in.readLine();
				System.out.println("来自服务端的消息：" + message);
				out.flush();
				ous.close();
				ins.close();
			}  
			
			 
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		} catch (IOException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null) {

				out.close();
			}
			if (in != null) {

				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			socket = null;

		}

	}

}
