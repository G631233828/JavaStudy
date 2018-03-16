package ¶ÁĞ´Ëø;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ¶ÁĞ´ËøÄ£Äâ»º´æ²Ù×÷ {

	private Map<String, Object> cache = new HashMap<String, Object>();
	
	
	

		public static void main(String[] args) {  
		   
			
			
		  
		
	}

	ReadWriteLock rw = new ReentrantReadWriteLock();
	

	public Object getData(String key) {

		
		rw.readLock().lock();// ´´½¨¶ÁËø

		Object value = null;

		try {
			value = cache.get(key);
			if (value == null) {
				
				rw.readLock().unlock();//½â³ı¶ÁËø
				rw.writeLock().lock();//´´½¨Ğ´Ëø

				try {
					if (value == null) {
						value = "aaa";
					}

				} finally {
					rw.writeLock().unlock();//½â³ıĞ´Ëø
				}

			}
		} finally {
			rw.readLock().unlock();//½â³ı¶ÁËø

		}

		return value;

	}

}
