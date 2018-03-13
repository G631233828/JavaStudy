package 多线程线程通信;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Condition轮流依次输出ABC {
	
	Lock lock= new ReentrantLock();
	
	Condition ca = lock.newCondition();
	Condition cb = lock.newCondition();
	Condition cc = lock.newCondition();
	static int count=1;
	
	public static void main(String[] args) {
		final Condition轮流依次输出ABC abc = new Condition轮流依次输出ABC();
		for(int i=0;i<100;i++){
		new Thread(new Runnable() {
			
			public void run() {
				
				try {
					abc.OutA(abc);
					abc.OutB(abc);
					abc.OutC(abc);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
			}
		}).start();
		}
		
	}
	public  void OutA(Condition轮流依次输出ABC v) throws InterruptedException{
		lock.lock();
			if(v.count == 1){
				System.out.println("A:"+v.count);
				v.count=2;
				ca.await();
			}
			cb.signal();
		lock.unlock();
	} 
	public  void OutB(Condition轮流依次输出ABC v) throws InterruptedException{
		lock.lock();
		if(v.count == 2){
			System.out.println("B:"+v.count);
			v.count=3;
			cb.await();
		}
		cc.signal();
	lock.unlock();
	} 
	
	public  void OutC(Condition轮流依次输出ABC v) throws InterruptedException{
		lock.lock();
		if(v.count == 3){
			System.out.println("C:"+v.count);
			v.count=1;
			cc.await();
		}
		ca.signal();
	lock.unlock();
	} 

}


class value{
	
	static	String  value="A";
}










