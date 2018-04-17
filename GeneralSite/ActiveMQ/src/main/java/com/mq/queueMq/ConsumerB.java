package com.mq.queueMq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ConsumerB {
	
	
	private ConnectionFactory connectionFactory;
	
	private Connection connection;
	
	private Session session;
	
	private MessageConsumer messageConsumer;
	
	private Destination destination;
	
	
	public ConsumerB(){
		try {
			this.connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
			this.connection = this.connectionFactory.createConnection();
			this.connection.start();
			this.session =this.connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void receiver(){
		try {
			this.destination = this.session.createQueue("first");
			this.messageConsumer=this.session.createConsumer(destination);
			this.messageConsumer.setMessageListener(new LintenerA());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		ConsumerB a = new ConsumerB();
		a.receiver();
	}
	
	
	
	
	

}
