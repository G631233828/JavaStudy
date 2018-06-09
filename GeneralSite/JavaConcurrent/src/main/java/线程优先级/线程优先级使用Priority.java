package 线程优先级;

public class 线程优先级使用Priority {
	
	
	public static void main(String[] args) {
		
		new Thread(new Runnable() {
			
			public void run() {
				Thread.currentThread().setPriority(2);
				System.out.println(1);
			}
		}).start();
		new Thread(new Runnable() {
			
			public void run() {
				Thread.currentThread().setPriority(1);
				System.out.println(2);
			}
		}).start();
		new Thread(new Runnable() {
			
			public void run() {
				Thread.currentThread().setPriority(9);
				System.out.println(3);
			}
		}).start();
		new Thread(new Runnable() {
			
			public void run() {
				Thread.currentThread().setPriority(4);
				System.out.println(4);
			}
		}).start();
		new Thread(new Runnable() {
			
			public void run() {
				Thread.currentThread().setPriority(5);
				System.out.println(5);
			}
		}).start();
		new Thread(new Runnable() {
			
			public void run() {
				Thread.currentThread().setPriority(6);
				System.out.println(6);
			}
		}).start();
		new Thread(new Runnable() {
			
			public void run() {
				Thread.currentThread().setPriority(10);
				System.out.println(7);
				
			}
		}).start();
		new Thread(new Runnable() {
			
			public void run() {
				Thread.currentThread().setPriority(7);
				System.out.println(8);
			}
		}).start();
		new Thread(new Runnable() {
			
			public void run() {
				Thread.currentThread().setPriority(3);
				System.out.println(9);
			}
		}).start();
		new Thread(new Runnable() {
			
			public void run() {
				Thread.currentThread().setPriority(8);
				System.out.println(10);
			}
		}).start();
		
		
	}

}
