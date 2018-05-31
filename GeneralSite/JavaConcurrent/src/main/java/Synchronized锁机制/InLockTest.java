package Synchronized锁机制;

/**
 * 在没有添加类锁的情况下输出是不会按照顺序来执行，在执行线程a的过程中也会执行线程b
 * 添加了类锁之后需要等线程a输出完成之后才会去执行线程b
 * @author fliay
 *
 */

public class InLockTest extends Thread{
	
	private String type;
	
	public InLockTest(String type){
		this.type = type;
	}
	
	@Override
	public void run() {
		print(type);
		
	}
	
	/**
	 * 通过使用 static+synchronized类创建一个内锁，类锁是按照顺序执行，是一个阻塞的
	 * @param type
	 */
	public static synchronized void print(String type){
		
		if(type =="a"){
			System.out.println("input a");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("input b");
		}
		System.out.println("success input:"+type);
	}
	
	
	
	public static void main(String[] args) {
		
		//创建两个线程执行
		InLockTest a = new InLockTest("a");
		InLockTest b = new InLockTest("b");
		a.start();
		b.start();
		
		
		
	}
	
	
	
	
	
	
	
	
}
