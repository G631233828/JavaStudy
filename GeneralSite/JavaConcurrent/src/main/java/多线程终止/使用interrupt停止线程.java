package 多线程终止;

public class 使用interrupt停止线程 {

	public static void main(String[] args) {

		Thread t =new Thread(new Runnable() {

			public void run() {
				for(int i=0;i<1000000;i++){
					
					System.out.println(i);
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Thread.interrupted();
			}
		});
		t.start();
		try {
			t.sleep(2000);
			t.interrupt();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		

	}
}
