package com.java.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class 获取网站的所有IP {
	
	
	public static void main(String[] args) {
		try {
			//获取百度的所有ip
			InetAddress[] in =getIps("www.baidu.com");
			
			for(int i =0;i<in.length;i++){
				
				System.out.println(in[i]);
				
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 
	 * @param url
	 * @return
	 * @throws UnknownHostException
	 * 返回url
	 */
	public static InetAddress[] getIps(String url) throws UnknownHostException{
		
		return InetAddress.getAllByName(url);
		
	}
	
	
	
	
	
	
	
	
	
}








