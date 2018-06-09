package 多线程并发包;

import java.util.LinkedList;
import java.util.Queue;

public class 生产者消费者模式 {

	public static void main(String[] args) {
		Queue<String> queue = new LinkedList<String>();
		int maxSize = 20;
		Thread producer = new Thread(new Producer(queue, maxSize));
		Thread consumer = new Thread(new Consumer(queue, maxSize));
		producer.start();
		consumer.start();
	}

}

class Producer implements Runnable {

	// 生产者队列
	private final Queue<String> queue;
	// 生产者最大数量
	private final int maxSize;

	public Producer(Queue<String> queue, int maxSize) {
		this.queue = queue;
		this.maxSize = maxSize;
	}

	public void run() {

		try {
			product();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void product() throws InterruptedException {

		while (true) {
			try {
				Thread.sleep((long)Math.random()*5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			synchronized (queue) {

				if (queue.size() == maxSize) {
					System.out.println("生产者：仓库已经满了，等待消费");
					// 生产者开始等待
					queue.wait();
				}

				System.out.println("生产者：生产了商品" + queue.add("product"));
				// 通知所有
				queue.notifyAll();
			}

		}

	}

}

class Consumer implements Runnable {

	private final Queue<String> queue;

	private final int maxSize;

	public Consumer(Queue<String> queue, int maxSize) {
		this.queue = queue;
		this.maxSize = maxSize;
	}

	public void run() {

		try {
			sale();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sale() throws InterruptedException {

		while (true) {
			try {
				Thread.sleep((long)Math.random()*5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			synchronized (queue) {

				if (queue.isEmpty()) {
					System.out.println("消费者：仓库空了，快点生产！");
					queue.wait();
				}
				System.out.println("消费者：消费了商品" + queue.remove());
				queue.notifyAll();
			}

		}

	}

}
