package 线程返回值;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class 线程返回结果CallableAndFuture {

	public static void main(String[] args) throws TimeoutException {
		
		//创建一个单实例的线程
		ExecutorService e = Executors.newSingleThreadScheduledExecutor();
		//使用Future来接收返回值
		Future<String> f=
		//创建一个可以进行返回数据的线程
		e.submit(new Callable<String>() {

			public String call() throws Exception {
				Thread.sleep(100);
				return "你好，线程！";
			}
		});
		
		System.out.println("正在等待线程返回结果...");
		try {
			//System.out.println("要求现在在1秒内返回数据："+f.get(1, TimeUnit.SECONDS));
			System.out.println("线程返回结果："+f.get());
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		//创建10个线程池来执行
		ExecutorService e2=Executors.newFixedThreadPool(10);
		//执行多个线程并且返回值
		CompletionService<Integer> c = new ExecutorCompletionService<Integer>(e2);
		
		for(int i=0;i<10;i++){
			final int value=i;
		c.submit(new Callable<Integer>() {
			public Integer call() throws Exception {
				Thread.sleep(new Random().nextInt(5000));
				// TODO Auto-generated method stub
				return value;
			}
		});
		}
		
		for(int i=0;i<10;i++){
			try {
				System.out.println(c.take().get());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		
	}

	
	
	
	
	
}
