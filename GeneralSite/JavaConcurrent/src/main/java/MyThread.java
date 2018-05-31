
public class MyThread extends Thread {

	private int count =5;
	
//	public MyThread(int count){
//		this.count = count;
//	}
	
	
	@Override
	public void run() {
		synchronized(this){
			count --;
			System.out.println(Thread.currentThread().getName()+":"+count);
		}
	
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		
		MyThread mth = new MyThread();
		
		Thread m1 = new  Thread(mth);
		Thread m2 = new  Thread(mth);
		Thread m3 = new  Thread(mth);
		Thread m4 = new  Thread(mth);
		Thread m5 = new  Thread(mth);
		
		m1.start();
		m2.start();
		m3.start();
		m4.start();
		m5.start();

	}

}
