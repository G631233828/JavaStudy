package 多线程并发包;

public class 多线程join的使用 {
	
	
	
public static void main(String[] args) throws InterruptedException {
	Thread t01 =new Thread(new thread01());
	Thread t02 =new Thread(new thread02());
	t01.start();
	t02.start();
	t01.join(); //使用了join之后只有在线程结束之后才会执行下面的system.out.printLn()
	t02.join();
	System.out.println("线程结束");
}
	
}


class thread01 implements Runnable{

	public void run() {
		for(int i=0;i<100;i++){
			System.out.println("线程1"+new Thread().getName()+":"+i);
		}
	}
	
}


class thread02 implements Runnable{

	public void run() {
		for(int i=0;i<100;i++){
			System.out.println("线程2"+new Thread().getName()+":"+i);
		}
	}
	
}
