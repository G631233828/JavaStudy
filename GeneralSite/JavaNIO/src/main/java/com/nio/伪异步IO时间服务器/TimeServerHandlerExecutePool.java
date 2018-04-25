package com.nio.伪异步IO时间服务器;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServerHandlerExecutePool {

	// 定义线程池
	private ExecutorService executor;
	
	/**
	 * 
	 * @param maxPoolSize  最大线程数
	 * @param queueSize    队列大小
	 */
	public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize) {
		executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));

	}
	
	public void execute(Runnable task){
		executor.execute(task);
	}

}
