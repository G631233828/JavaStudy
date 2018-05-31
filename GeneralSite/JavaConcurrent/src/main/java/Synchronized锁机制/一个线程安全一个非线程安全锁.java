package Synchronized锁机制;

public class 一个线程安全一个非线程安全锁 {
	
	
	
	
	
	/**
	 * method1使用线程安全
	 */
	public synchronized void method1(){
		System.out.println("method1");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * method2使用非线程安全
	 */
	public /*synchronized*/ void method2(){
		System.out.println("method2");
		
	}
	
	
	
	/**
	 * 如果method2没有使用synchronized那么两个线程会同时执行
	 * 如果method2添加了synchronized那么会等待method1执行完成之后再执行method2
	 * @param args
	 */
	public static void main(String[] args) {
		final 一个线程安全一个非线程安全锁 o = new 一个线程安全一个非线程安全锁();

		Thread t1 = new Thread(new Runnable() {
			
			public void run() {
				o.method1();
			}
		});
		t1.start();
		
		
		Thread t2 = new Thread(new Runnable() {
			
			public void run() {
				o.method2();
			}
		});
		t2.start();
		
		

	}

}
