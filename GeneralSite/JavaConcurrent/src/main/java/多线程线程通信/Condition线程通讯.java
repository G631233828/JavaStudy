package 多线程线程通信;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Condition线程通讯 {

	public static void main(String[] args) {

		final Condition线程通讯 t = new Condition线程通讯();

		new Thread(new Runnable() {

			public void run() {
				for (int i = 0; i < 10; i++) {
					t.add(0);
				}
			}
		}).start();

		new Thread(new Runnable() {

			public void run() {
				for (int i = 0; i < 10; i++) {
					t.del();
				}
			}
		}).start();

	}

	private List list = new ArrayList();

	private Lock lock = new ReentrantLock();

	Condition conditionadd = lock.newCondition();

	Condition conditiondel = lock.newCondition();

	public void add(int j) {
		// 上锁
		lock.lock();
		try {
			for (int i = 0; i < 10; i++) {
				Thread.sleep(200);
				list.add(i);
				System.out.println("正在添加元素" + i);
				if (list.size() == 10) {
					conditionadd.await();
					break;
				}
			}
			conditiondel.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	public void del() {

		lock.lock();

		try {
			for (int i = 0; i <list.size(); i++) {
				Thread.sleep(200);
					System.out.println("正在删除元素" + list.get(0));
				list.remove(0);
				if (list.size() == 0) {
					conditiondel.await();
				}

			}
			conditionadd.signal();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
	
	
}
