package 线程互斥锁;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class 线程互斥 {
	//如果不调用线程安全的锁会出现将两个name输出在一行中
	public static void main(String[] args) {
		
		ExecutorService e =  Executors.newFixedThreadPool(2);
		
		
		Thread t1 = new Thread(new t01("t01"));
		Thread t2 = new Thread(new t02("t02"));
		
		e.execute(t1);
		e.execute(t2);
		
	}
	

}


class  t01 implements Runnable{

	private final String name;
	
	public t01(String name){
		this.name = name;
	}
	
	public void run() {
		synchronized (t01.class) {//添加同步
			OutPut.out(name);
		}
	
		
	}
}
class t02 implements Runnable{
	
	private final String name;
	
	public t02(String name){
		this.name = name;
	}
	
	public void run() {
		
		synchronized (t02.class) {//添加同步
			OutPut.out(name);
		}
		
	}
}



class OutPut{
	
	
	public static void out(String name){
		while(true){
		System.out.print(name);
		}
	}
	
	
}




