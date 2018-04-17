package com.mq.queueMq;

import javax.jms.JMSException;
import javax.jms.MapMessage;

public class MessageTack implements Runnable {
	
	private MapMessage mapMessage;

	
	
	public MapMessage getMapMessage() {
		return mapMessage;
	}



	public void setMapMessage(MapMessage mapMessage) {
		this.mapMessage = mapMessage;
	}

	public MessageTack(MapMessage mapMessage){
		this.mapMessage = mapMessage;
	}
	

	public void run() {
		
		try {
			Thread.sleep(500);
	/*		msg.setInt("id", id);
			msg.setString("name", "张"+i);
			msg.setString("age", ""+i);
			String receiver = id%2==0?"A":"B";
			msg.setString("receiver", receiver);*/
			System.out.println("当前线程："+Thread.currentThread().getName()+"处理任务,"+this.mapMessage.getString("receiver")+"id="+this.mapMessage.getString("id"));
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
