package 多线程其他同步包;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exchange线程数据交换 {

	public static void main(String[] args) {

		ExecutorService e = Executors.newCachedThreadPool();

		final Exchanger<String> exchange = new Exchanger<String>();

		e.execute(new Runnable() {

			public void run() {
				try {
					String data1 = "线程1数据";
					Thread.sleep((long) Math.random() * 1000);
					System.out.println("1正在准备用数据" + data1 + "进行交换数据");
					data1 = exchange.exchange(data1);
					System.out.println("1交换后的数据：" + data1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		e.execute(new Runnable() {
			public void run() {
				try {
					String data1 = "线程2数据";
					Thread.sleep((long) Math.random() * 1000);
					System.out.println("2正在准备用数据" + data1 + "进行交换数据");
					data1 = exchange.exchange(data1);
					System.out.println("2交换后的数据：" + data1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}

}
