package 多线程队列;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

public class 同步队列SynchronousQueue {

	
	public static void main(String[] args) throws InterruptedException {
		
		final Semaphore semaphore = new Semaphore(1);//使用一个信号量来控制访问
		
		final SynchronousQueue<String> queue = new SynchronousQueue<String>();
		
		for(int i=0;i<10;i++){
			new Thread(new Runnable() {
				
				public void run() {
					
						try {
							semaphore.acquire();
							System.out.println(Thread.currentThread().getName()+":"+queue.take());
							semaphore.release();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				}
			}).start();
		}
		
		
		for(int i=0;i<10;i++){
			String a =""+i;
			queue.put(a);
		}
		
		
	}
	
}
