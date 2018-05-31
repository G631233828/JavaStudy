package 自定义线程池;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {

	private int corePoolSize;
	private int maxNumPoolSize;
	private long keepAliveTime;
	private TimeUnit time;

/*	public MyThreadPool(int corePoolSize, int maxNumPoolSize, long KeepAliveTime, TimeUnit time) {

		this.corePoolSize = corePoolSize;
		this.maxNumPoolSize = maxNumPoolSize;
		this.keepAliveTime = keepAliveTime;
		this.time = time;
	}
*/
	// 创建阻塞队列
	//BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10000);
	//使用无界队列
	BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();

	// 创建线程池
	ExecutorService executor = new ThreadPoolExecutor(30, 30, 60L, TimeUnit.SECONDS, queue);

	public void execute(Object task) {
		System.out.println("当前队列大小"+queue.size());
		executor.execute(new Handler(task,queue));
	}

}
