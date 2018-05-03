package com.aio.test;

public class TimerServer {
	
	public static void main(String[] args) {
		
		int port = 8088;
		AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
		
		new Thread(timeServer,"AIO-AsyncTimeServerHandler-001").start();
		
	}
	

}
