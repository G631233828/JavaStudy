package 线程共享变量;

public class 实现共享对象数据值加加减减 {
	
	public static void main(String[] args) throws InterruptedException {
		final OB o = new OB();

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				for(int i=0;i<10;i++){
				o.plus();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				o.minus();
			}
		});
		t1.start();
		t2.start();
		
		
		new Thread(new method1(o)).start();
	//	new Thread(new method2(o)).start();
		System.out.println(o.i);
	}

}

class OB {

	public  int i = 0;
	
	public synchronized void plus(){
		i++;
	}
	
	public synchronized void minus(){
	
		i--;
		
	}
	
	
}


class method1 implements Runnable{
	
	private int i ;
	
	public method1(OB o){
		this.i= o.i;
	}

	public void run() {
		for(int j =0;j<100;j++){
			
			i++;
		}
	}
	
}
class method2 implements Runnable{
	
	private int i ;
	
	public method2(OB o){
		this.i= o.i;
	}
	
	public void run() {
		for(int j =0;j<100;j++){
			
			i--;
		}
	}
	
}
