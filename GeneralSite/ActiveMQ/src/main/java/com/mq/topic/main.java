package com.mq.topic;

import javax.jms.JMSException;

public class main {
	
	public static void main(String[] args) {
		订阅者1  d1 = new 订阅者1();
		订阅者2  d2 = new 订阅者2();
		订阅者3  d3 = new 订阅者3();
		try {
			d1.receive();
			d2.receive();
			d3.receive();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
