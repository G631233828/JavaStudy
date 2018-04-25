package com.mq.testMQ;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import net.sf.json.JSONObject;

public class Lintener implements MessageListener {

	
	//创建阻塞队列
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10000);
		
		//创建线程池
		ExecutorService executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), 20, 120L, TimeUnit.SECONDS, queue);
		

	
	
	public void onMessage(Message message) {
	
		
		executor.execute(new MessageTack(message));
		
		
	
	}

}
