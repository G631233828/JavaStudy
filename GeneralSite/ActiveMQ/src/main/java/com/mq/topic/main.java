package com.mq.topic;

import javax.jms.JMSException;

public class main {
	
	public static void main(String[] args) {
		������1  d1 = new ������1();
		������2  d2 = new ������2();
		������3  d3 = new ������3();
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