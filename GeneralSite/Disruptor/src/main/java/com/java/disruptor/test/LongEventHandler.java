package com.java.disruptor.test;

import com.lmax.disruptor.EventHandler;


/**
 * 事件消费者，也就是一个事件处理器，这个事件处理器简单地把事件中存储的数据打印到终端
 * @author fliay
 *
 */
public class LongEventHandler implements EventHandler<LongEvent> {

	public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
		System.out.println(longEvent.getValue());
	}

}
