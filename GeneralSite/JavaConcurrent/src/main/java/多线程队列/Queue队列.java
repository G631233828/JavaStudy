package 多线程队列;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Queue队列 {

	public static void main(String[] args) {

		final BlockingQueue queue = new ArrayBlockingQueue(3);

			// 创建两个线程执行对队列的操作
			new Thread(new Runnable() {

				public void run() {
					while (true) {
						try {
							Thread.sleep((long)Math.random()*10001);
							System.out.println(Thread.currentThread().getName()+"我要准备往队列里面放数据了");
							queue.put(1);
							System.out.println(Thread.currentThread().getName()+"已经成功向队列中放了一个数据，当前队列中已经有" + queue.size() + "个数据");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			}).start();
			
			new Thread(new Runnable() {
				
				public void run() {
					while (true) {
							try {
								Thread.sleep(100);
								System.out.println(Thread.currentThread().getName()+"我要从队列中获取数据了");
								queue.take();
								System.out.println(Thread.currentThread().getName()+"已经成功向队列中获取了一个数据，当前队列中已经有" + queue.size() + "个数据");
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
					
				}
			}).start();

		}


}
