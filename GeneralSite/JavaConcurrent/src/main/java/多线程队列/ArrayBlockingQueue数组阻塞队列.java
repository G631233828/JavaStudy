package 多线程队列;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueue数组阻塞队列 {

	
	public static void main(String[] args) {
		
		
		final ArrayBlockingQueue fairQueue = new  ArrayBlockingQueue(1);
		
		
		
		
		new Thread(new Runnable() {
			
			public void run() {
				while(true)
					try {
						Thread.sleep(1000);
						fairQueue.put("SET:"+(int)(Math.random()*1000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}).start();
		
		new Thread(new Runnable() {
			
			public void run() {
				
				while(true)
					try {
				
						
						System.out.println(fairQueue.take());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			
		}).start();
		
		
		
		
		
		
		
		
	}
	
}
