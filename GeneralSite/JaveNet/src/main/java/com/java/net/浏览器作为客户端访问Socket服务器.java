package com.java.net;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class 浏览器作为客户端访问Socket服务器 {

	public static void main(String[] args) throws IOException {

		ServerSocket ss = new ServerSocket(1010);
		
		while(true){
			Socket s = ss.accept();
			
			
			InputStream in = s.getInputStream();
			
			byte[] bin = new byte[1024];
			
			int inlen = 0;
			
			while((inlen = in.read(bin))!=-1){
				System.out.println(new String(bin,0,inlen));
			}
			
			System.out.println(s.getInetAddress().getHostAddress());

			s.shutdownInput();
			
			PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
			
			pw.println("GET / HTTP/1.1");
			pw.println("Accept: */*");
			pw.println("Accept-Language: zh-CN");
			pw.println("Host: 192.168.1.171:1010");
			pw.println("Connection: keep-alive");
			
			File f = new File("d://a.html");
			
			FileInputStream fis = new FileInputStream(f);
			
			byte[] b = new byte[1024];
			
			int len =0;
			
			while((len = fis.read(b))!=-1){
				
				pw.println(new String(b,0,len));
			}
			s.shutdownOutput();
		
		}
		
		
	}

}
