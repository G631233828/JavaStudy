package ¶ÁÐ´Ëø;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ¶ÁÐ´ËøÄ£Äâ»º´æ²Ù×÷ {

	private Map<String, Object> cache = new HashMap<String, Object>();
	
	
	

		public static void main(String[] args) {  
		    // TODO Auto-generated method stub  
		    try {  
		        long start = System.currentTimeMillis();  
		        Process process = Runtime.getRuntime().exec(  
		        new String[] { "wmic", "cpu", "get", "ProcessorId" });  
		        process.getOutputStream().close();  
		        Scanner sc = new Scanner(process.getInputStream());  
		        String property = sc.next();  
		        String serial = sc.next();  
		        System.out.println(property + ": " + serial);  
		        System.out.println("time:" + (System.currentTimeMillis() - start));  
		    } catch (IOException e) {  
		        // TODO Auto-generated catch block  
		        e.printStackTrace();  
		    }  
		  
		
	}

	ReadWriteLock rw = new ReentrantReadWriteLock();
	

	public Object getData(String key) {

		
		rw.readLock().lock();// ´´½¨¶ÁËø

		Object value = null;

		try {
			value = cache.get(key);
			if (value == null) {
				
				rw.readLock().unlock();//½â³ý¶ÁËø
				rw.writeLock().lock();//´´½¨Ð´Ëø

				try {
					if (value == null) {
						value = "aaa";
					}

				} finally {
					rw.writeLock().unlock();//½â³ýÐ´Ëø
				}

			}
		} finally {
			rw.readLock().unlock();//½â³ý¶ÁËø

		}

		return value;

	}

}
