package 多线程加单例模式;



/**
 * 多线程new实例获取hash值是否会出现多个的情况
 * @author fliay
 *
 */
public class SingletonThread {

	
	private  static SingletonThread st = null;
	
	/**
	 * 使用了双重来判断
	 * @return
	 */
	public static SingletonThread getSingletonThread(){
		if(st == null){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (SingletonThread.class) {
				if(st == null){
					st = new SingletonThread();
				}
			}
		}
	return st;
	}
	
	
	
	public static void main(String[] args) {
		
		//创建多线程判断实例对象
		new Thread(new Runnable() {
			
			public void run() {
				System.out.println(SingletonThread.getSingletonThread().hashCode());
			}
		}).start();
		
		new Thread(new Runnable() {
			
			public void run() {
				System.out.println(SingletonThread.getSingletonThread().hashCode());
			}
		}).start();
		
		new Thread(new Runnable() {
			
			public void run() {
				System.out.println(SingletonThread.getSingletonThread().hashCode());
			}
		}).start();
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
