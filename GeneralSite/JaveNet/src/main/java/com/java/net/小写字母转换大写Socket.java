package com.java.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class 小写字母转换大写Socket {

}

class InputSocket {

	public static void main(String[] args) throws UnknownHostException, IOException {

		// 创建socket连接
		Socket s = new Socket("192.168.1.171", 1000);

		OutputStream out = s.getOutputStream();


	/*	BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		

		if (buff.readLine() != null) {
			out.write(buff.readLine().toString().getBytes());
		}*/
		
		while(true){
			Scanner input = new Scanner(System.in);

			out.write(input.nextLine().getBytes());
			
			
			InputStream in = s.getInputStream();// 获取输入流

			byte[] b = new byte[1024];

			int len = in.read(b);

			System.out.println(new String(b, 0, len));

		}
	
	}

}

class exchangeSocket {

	public static void main(String[] args) throws IOException {

		ServerSocket ss = new ServerSocket(1000);

		Socket s = ss.accept();
		while(true){

			InputStream in = s.getInputStream();

			byte[] b = new byte[1024];

			int len = in.read(b);

			String a = new String(b, 0, len);


			OutputStream out = s.getOutputStream();

			out.write(a.toUpperCase().getBytes());

		}
		

	}
}
