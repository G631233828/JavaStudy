package 多线程队列;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueue链表结构队列 {
	
	
	/**
	 * 链表有序输出
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		
		LinkedBlockingQueue  lbq = new LinkedBlockingQueue();
			lbq.put("A");
			lbq.put("D");
			lbq.put("B");
			lbq.put("E");
			lbq.put("C");
		
		System.out.println(lbq.size());
		
		while(lbq.size()>0){
			System.out.println(lbq.take());
		}
		
	
	}
	

}
