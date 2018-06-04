package com.java.disruptor.test;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class LongEventMain {

	public static void main(String[] args) {
		//创建缓冲池
		ExecutorService executor = Executors.newCachedThreadPool();
		
		//创建工厂
		LongEventFactory factory = new LongEventFactory();
		//创建bufferSize,也就是RingBuffer的大小，必须是2的N次方
		int ringBufferSize = 1024*1024;
		
		//创建disruptor实例
		//1.第一个参数为工厂类，用于创建一个个的LongEvent。LongEvent是实际的消费数据
		//2.第二个参数为缓冲区大小
		//3.第三参数线程池Disruptor内部的数据处理调度
		//4.ProducerType.SINGLE 和 ProducerType.MULTI  一个和多高生产者
		//5.第五个参数是一种策略：WaitStrategy
		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, ringBufferSize, executor,ProducerType.SINGLE,new YieldingWaitStrategy());
		
		//连接消费事件方法
		disruptor.handleEventsWith(new LongEventHandler());//消费者
		
		//启动
		disruptor.start();
		
		//disruptor的时间发布过程是一个两阶段提交的过程
		//使用该方法获得具体存放数据的容器ringBuffer（环形结构）
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
		
		//LongEventProducer producer = new LongEventProducer(ringBuffer);
		LongEventProducerWithTranslator  producer = new LongEventProducerWithTranslator(ringBuffer);
		
		ByteBuffer byteBuffer =  ByteBuffer.allocate(8);
		for(long i =0;i<100;i++){
			byteBuffer.putLong(0, i);
			producer.onData(byteBuffer);
			
		}
		disruptor.shutdown();
		executor.shutdown();
		
	}
	
	
}
