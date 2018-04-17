package com.mq.p2p;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {

	//public final String SELECTOR_0 ="age > 25";
	
	public final String SELECTOR_1="color = 'blue'";
	
	public final String SELECTOR_2="color = 'blue' AND money>2000";
	
	public final String SELECTOR_3="receiver = 'A'";
	
	//public final String SELECTOR_4 = "receiver = 'B'";
	
	
	//1连接工厂
	private ConnectionFactory connectionFactory;
	
	//2连接对象
	private Connection connection;
	
	//3session对象
	private Session session;
	
	//4消费者
	private MessageConsumer messageConsumer;
	
	//5 目标地址
	private Destination destination;
	
	
	public Consumer(){
		
		try {
			this.connectionFactory=new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
			this.connection = this.connectionFactory.createConnection();
			this.connection.start();
			this.session = this.connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);//设置事务false ，自动签收
			this.destination = this.session.createQueue("first");
			this.messageConsumer = this.session.createConsumer(this.destination,SELECTOR_2);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	//创建监听用来接收数据
	public void receiver() throws JMSException{
		
		this.messageConsumer.setMessageListener(new Lintener());
		
		
	}
	
	public static void main(String[] args) throws JMSException {
		Consumer c = new Consumer();
		c.receiver();
	}
	
	
	
}
