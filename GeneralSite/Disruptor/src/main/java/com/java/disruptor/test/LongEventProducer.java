package com.java.disruptor.test;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

public class LongEventProducer {

	private RingBuffer<LongEvent> ringBuffer;

	public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	public void onData(ByteBuffer bb){
		//1.可以把ringBuffer看作是一个事件队列，那么next还是得到下一个事件
		long sequence = ringBuffer.next();
		try{
			//2.用上面的索引去除一个空的事件用于填充（获取该序号对应的事件对象）
		LongEvent event = ringBuffer.get(sequence);
			//3.获取要通过事件传递的业务数据
		event.setValue(bb.getLong(0));
		}finally{
			//4发布事件
			//注意最后的ringBuffer.publish 方法必须包含在finally中 确保必须得到调用，如果某个请求的sequence未被提交，
			ringBuffer.publish(sequence);
		}
		
		
	}

}
