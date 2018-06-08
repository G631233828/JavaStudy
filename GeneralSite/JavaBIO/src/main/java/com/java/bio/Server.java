 package com.java.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	static int port = 8888;

	public static void main(String[] args) {
		ServerSocket server = null;

		try {
			server = new ServerSocket(port);

			System.out.println("server start 。。。。");
			
			
			//ExecutorService e =Executors.newScheduledThreadPool(10);
			
			HandlerExecutorPool e = new HandlerExecutorPool(20,1000);
			
			
			while (true) {
				// 进行阻塞
				Socket socket = server.accept();
				
				//调用线程处理
				e.execute(new ServerHandler(socket));
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
