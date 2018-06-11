package com.nio.伪异步IO时间服务器;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServerHandlerExecutePool {

	// �����̳߳�
	private ExecutorService executor;
	
	/**
	 * 
	 * @param maxPoolSize  ����߳���
	 * @param queueSize    ���д�С
	 */
	public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize) {
		executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));

	}
	
	public void execute(Runnable task){
		executor.execute(task);
	}

}
