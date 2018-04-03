package 读写锁;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock读写锁 {

	public static void main(String[] args) {
		final ReadWriteLockDemo r = new ReadWriteLockDemo();
		
		new Thread(new Runnable() {
			
			public void run() {
				r.get();
			
				
			}
		}).start();
	
		
	new Thread(new Runnable() {
			
			public void run() {
				r.put("1111");
			
				
			}
		}).start();
	
		

	}

}

class ReadWriteLockDemo {
	 private Object data = null;//共享数据，只能有一个线程能写该数据，但可以有多个线程同时读该数据。
	    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	    public void get(){
	        rwl.readLock().lock();//上读锁，其他线程只能读不能写
	        System.out.println(Thread.currentThread().getName() + " be ready to read data!");
	        
	        System.out.println(Thread.currentThread().getName() + "have read data :" + data);       
	        rwl.readLock().unlock(); //释放读锁，最好放在finnaly里面
	    }
	     
	    public void put(Object data){
	 
	        rwl.writeLock().lock();//上写锁，不允许其他线程读也不允许写
	        System.out.println(Thread.currentThread().getName() + " be ready to write data!");                   
	    
	        this.data = data;       
	        System.out.println(Thread.currentThread().getName() + " have write data: " + data);                   
	         
	        rwl.writeLock().unlock();//释放写锁   
	    }
	
	
	
	

}