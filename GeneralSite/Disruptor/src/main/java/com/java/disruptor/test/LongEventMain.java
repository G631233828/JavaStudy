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
		//���������
		ExecutorService executor = Executors.newCachedThreadPool();
		
		//��������
		LongEventFactory factory = new LongEventFactory();
		//����bufferSize,Ҳ����RingBuffer�Ĵ�С��������2��N�η�
		int ringBufferSize = 1024*1024;
		
		//����disruptorʵ��
		//1.��һ������Ϊ�����࣬���ڴ���һ������LongEvent��LongEvent��ʵ�ʵ���������
		//2.�ڶ�������Ϊ��������С
		//3.���������̳߳�Disruptor�ڲ������ݴ�������
		//4.ProducerType.SINGLE �� ProducerType.MULTI  һ���Ͷ��������
		//5.�����������һ�ֲ��ԣ�WaitStrategy
		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, ringBufferSize, executor,ProducerType.SINGLE,new YieldingWaitStrategy());
		
		//���������¼�����
		disruptor.handleEventsWith(new LongEventHandler());//������
		
		//����
		disruptor.start();
		
		//disruptor��ʱ�䷢��������һ�����׶��ύ�Ĺ���
		//ʹ�ø÷�����þ��������ݵ�����ringBuffer�����νṹ��
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