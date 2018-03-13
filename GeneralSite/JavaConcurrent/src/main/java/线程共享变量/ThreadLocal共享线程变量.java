package 线程共享变量;

import java.util.Date;

public class ThreadLocal共享线程变量 {

	public static ThreadLocal<PubParam> tl = new ThreadLocal<PubParam>();

	public static void main(String[] args) throws InterruptedException {
		int i=0;
		for ( ; i < 3; i++) {

			final int a =i;
			new Thread(new Runnable() {

				public void run() {
					
					PubParam p = new PubParam();
					p.setId("id=" + String.valueOf( new Date().getTime())+","+String.valueOf(a));
					p.setName("name=" + String.valueOf( new Date().getTime())+","+String.valueOf(a));
					tl.set(p);
					A.getA();
					B.getB();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}).start();

		}
	
	}

}

class A{
	
	
	public static void getA(){
		PubParam  p =ThreadLocal共享线程变量.tl.get();
System.out.println("A:"+p);		
		
	}
	
	
}


class B{
	
	
	public static void getB(){
		PubParam  p =ThreadLocal共享线程变量.tl.get();
		System.out.println("B:"+p);		
		
	}
	
	
}









class PubParam {

	public String id;
	public String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PubParam [id=" + id + ", name=" + name + "]";
	}

}