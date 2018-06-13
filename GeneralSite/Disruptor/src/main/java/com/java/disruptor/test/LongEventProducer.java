package com.java.disruptor.test;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

public class LongEventProducer {

	private RingBuffer<LongEvent> ringBuffer;

	public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	public void onData(ByteBuffer bb){
		//1.���԰�ringBuffer������һ���¼����У���ônext���ǵõ���һ���¼�
		long sequence = ringBuffer.next();
		try{
			//2.�����������ȥ��һ���յ��¼�������䣨��ȡ����Ŷ�Ӧ���¼�����
		LongEvent event = ringBuffer.get(sequence);
			//3.��ȡҪͨ���¼����ݵ�ҵ������
		event.setValue(bb.getLong(0));
		}finally{
			//4�����¼�
			//ע������ringBuffer.publish �������������finally�� ȷ������õ����ã����ĳ�������sequenceδ���ύ��
			ringBuffer.publish(sequence);
		}
		
		
	}

}