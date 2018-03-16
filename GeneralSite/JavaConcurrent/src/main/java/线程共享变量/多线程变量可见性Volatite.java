package 线程共享变量;

import java.util.concurrent.CopyOnWriteArrayList;

public class 多线程变量可见性Volatite extends Thread {
	
	
	private volatile boolean flag = true;
	
	

	public boolean isFlag() {
		return flag;
	}



	public void setFlag(boolean flag) {
		this.flag = flag;
	}



	public void run() {
		while(this.isFlag()){
			System.out.println("线程运行中。。。"+this.isFlag());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("线程已经停止！");
		
	}
	
	
	
	public static void main(String[] args) throws InterruptedException {
		
		
		多线程变量可见性Volatite v = new 多线程变量可见性Volatite();

		v.start();
		
		
		Thread.sleep(5000);
		
		v.setFlag(false);
		
		System.out.println("flag设置为false");
		
		
	}

}
