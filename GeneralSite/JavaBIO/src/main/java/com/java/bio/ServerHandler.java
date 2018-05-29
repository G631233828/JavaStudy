package com.java.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;

public class ServerHandler implements Runnable {

	Socket socket;
	
	BufferedReader in = null;
	PrintWriter out =null;
	
	public ServerHandler(Socket socket) {
		super();
		this.socket = socket;
	}

	public void run() {
		try {
			System.out.println("当前处理线程："+Thread.currentThread().getName());
			
			InputStream ins = socket.getInputStream();
			OutputStream ous = socket.getOutputStream();
			in = new BufferedReader(new InputStreamReader(ins));
			out = new PrintWriter(ous,true);
			String message = in.readLine();
			System.out.println("来自客户端的消息："+message);
			out.write("你好客户端，服务器已经收到消息");
			out.flush();
			ous.close();
			ins.close();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
	}

}
