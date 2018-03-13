package 多线程其他同步包;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrier同步包 {
	
	
	
	
	
	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		
		
		final CyclicBarrier cb = new CyclicBarrier(5);
		
		for(int i=0;i<5;i++){
			
			new Thread(new Runnable() {
				
				public void run() {

					try {
						Thread.sleep((long) (Math.random()*10001));
						System.out.println(Thread.currentThread().getName()+"，当前地点A已经有"+(cb.getNumberWaiting()+1)+"人已经到达"+(cb.getNumberWaiting()==4?"人都到了继续走":"还有同伴没到，还需等待！。。。"));
						cb.await();
				                  
						Thread.sleep((long) (Math.random()*10001));
						System.out.println(Thread.currentThread().getName()+"，当前地点A已经有"+(cb.getNumberWaiting()+1)+"人已经到达"+(cb.getNumberWaiting()==4?"人都到了继续走":"还有同伴没到，还需等待！。。。"));
						cb.await();
						
						Thread.sleep((long) (Math.random()*10001));
						System.out.println(Thread.currentThread().getName()+"，当前地点A已经有"+(cb.getNumberWaiting()+1)+"人已经到达"+(cb.getNumberWaiting()==4?"人都到了继续走":"还有同伴没到，还需等待！。。。"));
						cb.await();
						
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}).start();
			
		
	}

}
	
}
