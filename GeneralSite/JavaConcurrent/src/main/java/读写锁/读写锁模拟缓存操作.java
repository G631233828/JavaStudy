package 读写锁;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class 读写锁模拟缓存操作 {

	private Map<String, Object> cache = new HashMap<String, Object>();
	
	
	

		public static void main(String[] args) {  
		   
			
			
		  
		
	}

	ReadWriteLock rw = new ReentrantReadWriteLock();
	

	public Object getData(String key) {

		
		rw.readLock().lock();// 创建读锁

		Object value = null;

		try {
			value = cache.get(key);
			if (value == null) {
				
				rw.readLock().unlock();//解除读锁
				rw.writeLock().lock();//创建写锁

				try {
					if (value == null) {
						value = "aaa";
					}

				} finally {
					rw.writeLock().unlock();//解除写锁
				}

			}
		} finally {
			rw.readLock().unlock();//解除读锁

		}

		return value;

	}

}
