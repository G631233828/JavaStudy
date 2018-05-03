package com.mq.p2p;

import java.io.UnsupportedEncodingException;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.TextMessage;

import net.sf.json.JSONObject;

public class MessageTack implements Runnable {
	
	private Message message;

	public MessageTack(Message message){
		this.message = message;
	}
	

	public void run() {
		
		try {
			if (message instanceof TextMessage) {
				
				TextMessage ret = (TextMessage) message;
				System.out.println(ret.getText());
			JSONObject json = JSONObject.fromObject(ret);
				System.out.println(json.toString());
			}
			if (message instanceof MapMessage) {
				MapMessage ret = (MapMessage) message;
				
			
				  System.out.println(ret.toString());
				  System.out.println(ret.getString("name"));
				  System.out.println(ret.getString("age"));
				 System.out.println(ret.getStringProperty("color"));
				  System.out.println(ret.getStringProperty("money"));
				 

			}
			if (message instanceof BytesMessage) {
				try {
					BytesMessage ret = (BytesMessage) message;
					byte[] buff = new byte[(int) ret.getBodyLength()];
					for (int i = 0; i < ret.getBodyLength(); i++) {
						buff[i] = ret.readByte();
					}
					String msg = new String(buff, "UTF-8");

					System.out.println("收到消息：" + msg);
					JSONObject json = JSONObject.fromObject(msg);
					System.out.println(json.toString());

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}catch(JMSException e){
				e.printStackTrace();
			}
	

	}

}
