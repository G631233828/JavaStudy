package 多线程方法合集;

public class 线程暂停与继续 {

	
	public static void main(String[] args) throws InterruptedException {
		
		Thread t =new Thread(new MyThread());
		t.start();
		Thread.sleep(2000);
		t.interrupt();
	
	
}
}

class MyThread extends Thread{
	
	private long i =0;
	
	

	public long getI() {
		return i;
	}



	public void setI(long i) {
		this.i = i;
	}



	public void run() {

		while(true){
			if(MyThread.interrupted()){
				System.out.println("线程已经暂停了。。。。");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			i++;
		System.out.println(i);
		}
	}
	
	
	
	
}