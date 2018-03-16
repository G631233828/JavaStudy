package 原子性;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class 原子性数据类型Atomtic {
	// 原子性
	//static AtomicInteger age = new AtomicInteger(0);

	public static void main(String[] args) throws InterruptedException {

		/*
		 * 循环10000个线程实现++ for(int i =0;i<10000;i++){
		 * 
		 * new Thread(new Runnable() {
		 * 
		 * public void run() { age.incrementAndGet();//age++
		 * System.out.println(age.get()); } }).start(); }
		 */
		// 创建5个线程池
		ExecutorService e = Executors.newFixedThreadPool(3);

		AtomicInteger p = new AtomicInteger(200);
		//sale s = new sale(p);
		for (int i = 0; i < 1000; i++) {
			Thread t1 = new Thread(new sale(p,"柜台1") );
			Thread t2 = new Thread(new sale(p, "柜台2"));
			Thread t3 = new Thread(new sale(p, "柜台3"));
			Thread t4 = new Thread(new sale(p, "柜台4"));
			e.execute(t1);
			e.execute(t2);
			e.execute(t3);
			e.execute(t4);
		}
	}

}

class sale implements Runnable {
	private AtomicInteger p;
	private String name;

	public sale(AtomicInteger p,String name) {
		this.p = p;
		this.name = name;
	}

	public void run() {
		if (p.get() == 0) {
			return;
		} else {
			p.decrementAndGet();
			System.out.println(this.name + "》成功出售一张票，还剩余：" + p.get() + " 张票");
			try {
				Thread.sleep((long) (Math.random()*1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}