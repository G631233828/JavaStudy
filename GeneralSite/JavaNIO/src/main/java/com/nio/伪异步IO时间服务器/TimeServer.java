package com.nio.伪异步IO时间服务器;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

	public static void main(String[] args) throws IOException {

		int port = 8090;

		ServerSocket server = null;//
		try {
			server = new ServerSocket(port);

			System.out.println("The time server is start in port：" + port);

			Socket socket = null;

			TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 10000);// 创建I/O任务线程池   （线程数，队列大小）

			while (true) {
				//监听客户端socket
				socket = server.accept();
				singleExecutor.execute(new TimeServerHandler(socket));

			}

		} finally {
			if (server != null)
				System.out.println("the time server clolse");
			server.close();
			server = null;
		}

	}

}
