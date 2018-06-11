package 多线程其他同步包;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author fliay
 * 当countDownLatch设置为1的时候线程执行的时候执行await操作，
 * 需要对线程的countDownLatch值进行chutdown操作等于0的时候才会唤醒await的线程
 *
 */
public class CountdownLatch计数器 {

	public static void main(String[] args) {

		ExecutorService e = Executors.newCachedThreadPool();// 创建线程池

		final CountDownLatch cOrder = new CountDownLatch(1);
		CountDownLatch cAnswer = new CountDownLatch(3);

		for (int i = 0; i < 3; i++) {
			Runnable r = new Runnable() {

				public void run() {
					try {
						System.out.println("线程" + Thread.currentThread().getName() + "正准备接受命令");
						cOrder.await();
						// 战士们都处于等待命令状态
						System.out.println("线程" + Thread.currentThread().getName() + "已接受命令");
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程" + Thread.currentThread().getName() + "回应命令处理结果");
						cOrder.countDown(); // 任务执行完毕，返回给指挥官，cdAnswer减1。
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			};

			e.execute(r);
		}

		try {
			Thread.sleep((long) (Math.random() * 10000));

			System.out.println("线程" + Thread.currentThread().getName() + "即将发布命令");
			cOrder.countDown(); // 发送命令，cdOrder减1，处于等待的战士们停止等待转去执行任务。
			System.out.println("线程" + Thread.currentThread().getName() + "已发送命令，正在等待结果");
			cOrder.await(); // 命令发送后指挥官处于等待状态，一旦cdAnswer为0时停止等待继续往下执行
			System.out.println("线程" + Thread.currentThread().getName() + "已收到所有响应结果");
		} catch (Exception s) {
			s.printStackTrace();
		}
		e.shutdown(); // 任务结束，停止线程池的所有线程

	}

}
