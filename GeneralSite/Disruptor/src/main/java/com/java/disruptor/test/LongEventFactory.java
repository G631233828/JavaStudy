package com.java.disruptor.test;

import com.lmax.disruptor.EventFactory;
/**
 * 由于需要让Disruptor为我们创建事件，我们同时还声明了一个EventFactory来实例化Event对象。
 * @author fliay
 *
 */
public  class LongEventFactory implements EventFactory {

	public Object newInstance() {
		return new LongEvent();
	}

}
