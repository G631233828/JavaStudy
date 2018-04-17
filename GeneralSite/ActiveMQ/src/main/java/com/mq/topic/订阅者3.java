package com.mq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQXAConnectionFactory;

public class 订阅者3 {

	private ConnectionFactory connectionFactory;

	private Connection connection;

	private Session session;

	private Destination destination;

	private MessageConsumer messageConsumer;

	public 订阅者3() {
		try {
			this.connectionFactory = new ActiveMQXAConnectionFactory("admin", "admin", "tcp://localhost:61616");
			this.connection = this.connectionFactory.createConnection();
			this.connection.start();
			this.session = this.connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void receive() throws JMSException {

		this.destination = session.createTopic("topic1");
		this.messageConsumer = session.createConsumer(destination);
		this.messageConsumer.setMessageListener(new MessageListener() {
			public void onMessage(Message message) {
				try {
					if (message instanceof TextMessage) {
						System.out.println("订阅者3收到消息");
						TextMessage m = (TextMessage) message;

						System.out.println(m.getText());

					}
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

}
