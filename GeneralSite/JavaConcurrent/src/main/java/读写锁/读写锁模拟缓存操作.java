package ��д��;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ��д��ģ�⻺����� {

	private Map<String, Object> cache = new HashMap<String, Object>();
	
	
	

		public static void main(String[] args) {  
		   
			
			
		  
		
	}

	ReadWriteLock rw = new ReentrantReadWriteLock();
	

	public Object getData(String key) {

		
		rw.readLock().lock();// ��������

		Object value = null;

		try {
			value = cache.get(key);
			if (value == null) {
				
				rw.readLock().unlock();//�������
				rw.writeLock().lock();//����д��

				try {
					if (value == null) {
						value = "aaa";
					}

				} finally {
					rw.writeLock().unlock();//���д��
				}

			}
		} finally {
			rw.readLock().unlock();//�������

		}

		return value;

	}

}
