package 锁重入调用;

public class SynchronizedReuse {
	
	
	public synchronized void method1(){
		System.out.println("method1");
		method2();
	}

	public synchronized void method2() {
		System.out.println("method2");
		method3();
	}

	public synchronized void method3() {
		System.out.println("method3");
		method1();
	}
	
	
	public static void main(String[] args) {
		
		final SynchronizedReuse sr = new SynchronizedReuse();
		new Thread(new Runnable() {
			
			public void run() {
				sr.method1();
			}
		}).start();
		
		
		
	}
	
	
	
	

}
