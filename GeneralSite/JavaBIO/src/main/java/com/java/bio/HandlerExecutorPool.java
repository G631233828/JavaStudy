package com.java.bio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 * @author fliay
 *
 */
public class HandlerExecutorPool {
	
	
	private ExecutorService executor;
	
	public HandlerExecutorPool(int maxPoolSize,int queueSize){
		this.executor = new ThreadPoolExecutor(
				/*Runtime.getRuntime().availableProcessors()*/20,//虚拟机的数量
				maxPoolSize,//最大线程数
				120L, 
				TimeUnit.SECONDS, 
				new ArrayBlockingQueue<Runnable>(queueSize)//有界队列
				);
	}
	
	public void execute(Runnable task){
		this.executor.execute(task);
	}

}
