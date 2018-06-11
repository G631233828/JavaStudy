package com.java.net;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class TCP图片上传并发版本 {
	
	
	public static void main(String[] args) throws IOException {
		
		
		ServerSocket ss = new ServerSocket(1000);
		
		while(true){
			
			Socket s = ss.accept();
		
			new Thread(new TCPUploadServerThreads(s)).start();
			
		}
	}
	

}

class TCPUploadClientThreads {

	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket socket = new Socket("192.168.1.171", 1000);

		File f = new File("d://1.iso");

		FileInputStream fis = new FileInputStream(f);
		// 上传数据
		OutputStream out = socket.getOutputStream();

		int len = 0;

		byte[] b = new byte[1024];

		while ((len = fis.read(b)) != -1) {

			out.write(b, 0, len);

		}

		// 告诉服务器已经完成了上传
		socket.shutdownOutput();
		// 完成上传
		// 获取服务器反馈
		InputStream in = socket.getInputStream();

		byte[] inb = new byte[1024];

		len = in.read(inb);

		System.out.println(new String(inb, 0, len));
		fis.close();
		in.close();
		out.close();
		socket.close();

	}

}

class TCPUploadServerThreads implements Runnable {
	
	private Socket s;	
	
	TCPUploadServerThreads(Socket s){
		this.s=s;
	}

	public void run() {
		try {

			InputStream in = s.getInputStream();

			String fileName = new Date().getTime()+".iso";
			
			FileOutputStream fos = new FileOutputStream("d://"+fileName);

			byte[] b = new byte[1024];

			int len = 0;

			while ((len = in.read(b)) != -1) {

				fos.write(b, 0, len);
			}
			
			
			OutputStream out = s.getOutputStream();

			out.write("上传成功".getBytes());
			fos.close();
			out.close();
			in.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
