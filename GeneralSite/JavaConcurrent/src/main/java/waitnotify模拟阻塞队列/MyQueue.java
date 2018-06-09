package waitnotify模拟阻塞队列;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue {
	
	//定义一个结合用来存放队列
	private LinkedList<Object> lqList = new LinkedList<Object>();
	
	//定义最最低值
	private int minSize = 0;
	
	//定义最大值
	private int maxSize;
	
	//定义计数器
	private AtomicInteger count  = new AtomicInteger(0);
	
	//定义一个线程锁
	private Object lock = new Object();
	
	//定义构造方法
	public MyQueue(int maxSize){
		this.maxSize = maxSize;
	}
	
	
	//定义一个put方法，用来放队列中存放数据
	
	public void put(Object o){
		//对put方法进行加锁
		synchronized (lock) {
			//如果队列满了，就需要执行等待，不能再往里面放数据了
			while(count.get() == maxSize){
				//当前锁对象执行等待
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			lqList.add(o);
			count.getAndIncrement();//计数器+1
			System.out.println("已经向队列中添加了数据"+o);
			//通知可以那数据了
			lock.notify();
			
			
		}
	}
	
	//定义一个take方法，用来从队列中消费数据
	public Object take(){
		Object obj = null;
		synchronized (lock) {
			while(count.get()==minSize){
				//判断如果当前队列的大小等于最小值，那么需要执行等待
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//从集合中获取第一个元素
			obj = lqList.removeFirst();
			count.getAndDecrement();//计数器-1
			System.out.println("队列中已经移除了一条数据");
			lock.notify();
			
		}
		return obj;
	}
	
	
	public int size(){
		return count.get();
	}
	
	public static void main(String[] args) {
	
		final MyQueue mq = new MyQueue(5);
		
		mq.put("a");
		mq.put("b");
		mq.put("c");
		mq.put("d");
		mq.put("e");
		
		//创建现场往里面存数据
		new Thread(new Runnable() {
			
			public void run() {
				mq.put("f");
				mq.put("g");
			}
		}).start();
		//创建现场来取数据
		
		new Thread(new Runnable() {
			
			public void run() {
				
				Object o1 = mq.take();
				System.out.println("从队列中取出数据"+o1);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Object o2 = mq.take();
				System.out.println("从队列中取出数据"+o2);
				
			}
		}).start();
		
		
	}
	
	
	
	

}
