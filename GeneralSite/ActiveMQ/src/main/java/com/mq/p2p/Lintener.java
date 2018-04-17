package com.mq.p2p;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Lintener implements MessageListener {

	public void onMessage(Message message) {
		try{
			if(message instanceof TextMessage){
				
			}
			if(message instanceof MapMessage){
				MapMessage ret = (MapMessage) message;
				/*msg3.setString("name", "mary");
				msg3.setString("age", "30");
				msg3.setStringProperty("color","black");
				msg3.setIntProperty("money", 3500);*/
				System.out.println(ret.toString());
				System.out.println(ret.getString("name"));
				System.out.println(ret.getString("age"));
				System.out.println(ret.getStringProperty("color"));
				System.out.println(ret.getStringProperty("money"));
			}
			
			
		}catch(JMSException e){
			e.printStackTrace();
		}
	}

}
