package 多线程实现信号灯实现并发;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Semahore信号灯 {

	public static void main(String[] args) {
		final int bingfa=3;

		ExecutorService e = Executors.newFixedThreadPool(2);

		final Semaphore sp = new Semaphore(bingfa);

		for (int i = 0; i < 10; i++) {
			Runnable runnable = new Runnable() {

				public void run() {
					try {
						sp.acquire();
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("线程"+Thread.currentThread().getName()+"进入，当前已经有"+(bingfa-sp.availablePermits())+"个并发");
				
					try {
						Thread.sleep((long)(Math.random()*10000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					sp.release();
					System.out.println("线程"+Thread.currentThread().getName()+"已经离开，当前已经有"+(bingfa-sp.availablePermits())+"个并发");
					
				}
				
			};
			e.execute(runnable);
		}

	}

}
