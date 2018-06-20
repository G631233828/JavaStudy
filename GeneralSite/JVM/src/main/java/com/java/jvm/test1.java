package com.java.jvm;

public class test1 {

	public static void main(String[] args) {
		
		//-XX:+PrintGC -Xms5m -Xmx20m -XX:+UseSerialGc -XX:+PrintGCDetails
		
		byte[] b1 = new byte[1*1024*1024];
		System.out.println("分配了1M");
		System.out.println("max memory:"+Runtime.getRuntime().maxMemory());
		System.out.println("free memory:"+Runtime.getRuntime().freeMemory());
		System.out.println("total memory:"+Runtime.getRuntime().totalMemory());
		
		byte[] b2 = new byte[4*1024*1024];
		System.out.println("分配了4M");
		System.out.println("max memory:"+Runtime.getRuntime().maxMemory());
		System.out.println("free memory:"+Runtime.getRuntime().freeMemory());
		System.out.println("total memory:"+Runtime.getRuntime().totalMemory());
		
		while(true)
			try {
				byte[] b4 = new byte[4*1024*1024];
				System.out.println("分配了4M");
				System.out.println("max memory:"+Runtime.getRuntime().maxMemory());
				System.out.println("free memory:"+Runtime.getRuntime().freeMemory());
				System.out.println("total memory:"+Runtime.getRuntime().totalMemory());
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
