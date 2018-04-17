package com.mq.queueMq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQXAConnectionFactory;

public class Producer {


	private ConnectionFactory connectionFactory;
	
	private Connection connection;
	
	private Session session;
	
	
	private MessageProducer messageProducer;

	public Producer(){
		try {
			this.connectionFactory = new ActiveMQXAConnectionFactory("admin", "admin", "tcp://localhost:61616");
			this.connection = this.connectionFactory.createConnection();
			this.connection.start();
			this.session = this.connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			this.messageProducer = this.session.createProducer(null);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void send1(){
		try {
			Destination destination = this.session.createQueue("first");
			
			for(int i=0;i<100;i++){
				MapMessage msg = this.session.createMapMessage();
				int id=i;
				msg.setInt("id", id);
				msg.setString("name", "уе"+i);
				msg.setString("age", ""+i);
				String receiver = id%2==0?"A":"B";
				msg.setString("receiver", receiver);
				this.messageProducer.send(destination, msg, DeliveryMode.NON_PERSISTENT, 2, 1000*60*10L);
				System.out.println("message send id:"+id);
			}
			
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		Producer a = new Producer();
		a.send1();
	}
	
	
	
	
	
}
