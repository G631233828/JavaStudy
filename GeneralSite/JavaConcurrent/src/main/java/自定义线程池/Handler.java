package 自定义线程池;

import java.util.concurrent.BlockingQueue;

public class Handler implements Runnable {

	private Object task;
	private BlockingQueue<Runnable> queue;

	public Handler(Object task, BlockingQueue<Runnable> queue) {
		this.task = task;
		this.queue = queue;
	}

	public void run() {
		Student s = (Student) task;
		System.out.println(Thread.currentThread().getName() + "：" + s.getId() + "," + s.getAge() + "," + s.getName());
		System.out.println("队列中还剩下：" + queue.size() + "个元素");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
