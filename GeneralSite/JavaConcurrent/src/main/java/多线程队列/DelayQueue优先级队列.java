package 多线程队列;

import java.util.Calendar;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueue优先级队列 extends Thread {
	

		final static  DelayQueue<wangba> queue = new DelayQueue<wangba>();
		
		 public void onCumputer(Integer id, String name, Integer money) {
			 wangba w = new wangba(id, name, money);
		        queue.put(w);
		        System.out.println("身份证：" + id + "姓名：" + name + "上机时间" + money);
		    }

		    public void outCumputer(wangba w) {
		        System.out.println("身份证：" + w.getId() + "姓名：" + w.getName() + "下机");
		    }

		    public void run() {
		        while (queue.size()>0) {
		            try {
		            	wangba w = queue.take();
		                outCumputer(w);
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		        }

		    }

		    public static void main(String[] args) {

		    	DelayQueue优先级队列 queueTest = new DelayQueue优先级队列();
		        System.out.println("一群人去网吧开卡。。。");
		        new Thread(queueTest).start();
		        queueTest.onCumputer(123, "张三", 3);
		        queueTest.onCumputer(124, "李四", 5);
		        queueTest.onCumputer(126, "王五", 10);
	}

}

class wangba implements Delayed {

	// 身份证号
	private Integer id;
	// 姓名
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// 下机时间 一元一秒
	private long endTime;

	public wangba(Integer id, String name, Integer money) {
		this.id = id;
		this.name = name;
		this.endTime = money * 1000 + System.currentTimeMillis();
	}

	// 设置优先级
	public int compareTo(Delayed o) {
		wangba c = (wangba) o;
		return this.endTime - c.endTime > 0 ? 1 : (this.endTime - c.endTime < 0 ? -1 : 0);
	}

	// 判断过期时间
	public long getDelay(TimeUnit unit) {

		return this.endTime - System.currentTimeMillis();
	}

}