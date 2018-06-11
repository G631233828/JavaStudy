package com.mq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQXAConnectionFactory;

public class 新闻 {
	
	
	private ConnectionFactory connectionFactory;
	
	private Connection connection;
	
	private Session session;
	
	private Destination destination;
	
	private TextMessage textMessage;
	
	private MessageProducer messageProducer;

	public 新闻(){
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
	
public void sendMessage(){

	try {
		destination = session.createTopic("topic1");
		textMessage = session.createTextMessage("内容。。。。。。。。。。。。。");
		this.messageProducer.send(destination, textMessage);
		
	} catch (JMSException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	
public static void main(String[] args) {
	新闻 x= new 新闻();
	x.sendMessage();
}
	
}
