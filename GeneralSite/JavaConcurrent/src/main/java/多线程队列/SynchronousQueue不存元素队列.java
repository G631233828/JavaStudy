package 多线程队列;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueue不存元素队列 {

	public static void main(String[] args) {

		final SynchronousQueue sq = new SynchronousQueue();

		// sq.add("1"); 如果直接给队列复制输出的话会报错如下信息
		/*
		 * Exception in thread "main" java.lang.IllegalStateException: Queue
		 * full at java.util.AbstractQueue.add(Unknown Source) at
		 * 多线程队列.SynchronousQueue不存元素队列.main(SynchronousQueue不存元素队列.java:14)
		 */
		for(int i=0;i<10;i++){
		new Thread(new Runnable() {
			public void run() {
				while(true)
					try {
						System.out.println(sq.take());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}
		}).start();
		}
		
		
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					synchronized (SynchronousQueue不存元素队列.class) {
						
						if(sq.isEmpty()){
							try {
								sq.put(Math.random() * 1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else{
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				
				}

			}
		}).start();

	}

}
