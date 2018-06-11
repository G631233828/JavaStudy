package 线程锁机制;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class 线程锁Lock {

	public static void main(String[] args) {
		MyCount count = new MyCount("6222121001041899235", 20000);

		Lock lock = new ReentrantLock();

		ExecutorService e = Executors.newFixedThreadPool(5);
		Thread t1 = new Thread(new User("fliay", count, 2000, lock));
		Thread t2 = new Thread(new User("jay", count, -1000, lock));
		Thread t3 = new Thread(new User("fliay", count, 3000, lock));
		Thread t4 = new Thread(new User("jay", count, -3000, lock));
		Thread t5 = new Thread(new User("haha", count, 12000, lock));
		Thread t6 = new Thread(new User("fliay", count, -12000, lock));
		e.execute(t1);
		e.execute(t2);
		e.execute(t3);
		e.execute(t4);
		e.execute(t5);
		e.execute(t6);

	}

}

class User implements Runnable {

	private String name;
	private MyCount myCount;
	private int iocash;
	private Lock myLock;

	public User(String name, MyCount myCount, int iocash, Lock myLock) {
		this.name = name;
		this.myCount = myCount;
		this.iocash = iocash;
		this.myLock = myLock;
	}
 
	public void run() {
		String string;
		if (iocash > 0) {
			string = "存款";
		} else {
			string = "取款";
		}
		myLock.lock();
		// 执行现金任务
		System.out.println(name + "正在操作" + myCount + "账户，" + string + "金额为" + iocash + ",当前账户余额为：" + myCount.getCash());
		myCount.setCash(myCount.getCash() + iocash);
		System.out.println(name + "正在操作" + myCount + "账户，" + string + "金额为" + iocash + ",当前账户余额为：" + myCount.getCash());
		System.out.println("操作结束");
		myLock.unlock();
	}

}

class MyCount {
	private String oid;// 帐号
	private int cash;// 余额

	MyCount(String oid, int cash) {
		this.oid = oid;
		this.cash = cash;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	@Override
	public String toString() {
		return "MyCount [oid=" + oid + ", cash=" + cash + "]";
	}

}
