package com.java.bio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * �Զ����̳߳�
 * @author fliay
 *
 */
public class HandlerExecutorPool {
	
	
	private ExecutorService executor;
	
	public HandlerExecutorPool(int maxPoolSize,int queueSize){
		this.executor = new ThreadPoolExecutor(
				/*Runtime.getRuntime().availableProcessors()*/20,//�����������
				maxPoolSize,//����߳���
				120L, 
				TimeUnit.SECONDS, 
				new ArrayBlockingQueue<Runnable>(queueSize)//�н����
				);
	}
	
	public void execute(Runnable task){
		this.executor.execute(task);
	}

}