package com.mq.p2p;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
	
	//����ģʽ
	
	//1���ӹ���
	private ConnectionFactory connectionFactory;
	//2���Ӷ���
	private Connection connection;
	//3����session����
	private Session session;
	//4����������
	private MessageProducer messageProducer;
	
	//�����޲ι�����
	public Producer(){
		try{
			this.connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
			this.connection = this.connectionFactory.createConnection();
			this.connection.start();
			this.session= this.connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);//���ò���Ҫ����֧�֣������Զ�����
			this.messageProducer = this.session.createProducer(null);
			
		}catch(JMSException e){
			e.printStackTrace();
		}
	}
	
	//����session����
	public Session getSession(){
		return this.session;
	}
	
	public void send1(){
		try {
			Destination destination = this.session.createQueue("first");//��������
			//������Ϣ����
			MapMessage msg1 = this.session.createMapMessage();
			msg1.setString("name", "fliay");
			msg1.setString("age", "28");
			msg1.setStringProperty("color","red");
			msg1.setIntProperty("money", 2000);
			MapMessage msg2 = this.session.createMapMessage();
			msg2.setString("name", "jay");
			msg2.setString("age", "29");
			msg2.setStringProperty("color","blue");
			msg2.setIntProperty("money", 2500);
			MapMessage msg3 = this.session.createMapMessage();
			msg3.setString("name", "mary");
			msg3.setString("age", "30");
			msg3.setStringProperty("color","black");
			msg3.setIntProperty("money", 3500);
			MapMessage msg4 = this.session.createMapMessage();
			msg4.setString("name", "jack");
			msg4.setString("age", "27");
			msg4.setStringProperty("color","yellow");
			msg4.setIntProperty("money", 2500);
			
			
			//�������Ͷ�     �����յĶ��У����͵���Ϣ���־û���non�־û��������ȼ�������ʱ�䣩
			this.messageProducer.send(destination, msg1, DeliveryMode.NON_PERSISTENT, 2, 1000*60*10L);
			this.messageProducer.send(destination, msg2, DeliveryMode.NON_PERSISTENT, 4, 1000*60*10L);
			this.messageProducer.send(destination, msg3, DeliveryMode.NON_PERSISTENT, 6, 1000*60*10L);
			this.messageProducer.send(destination, msg4, DeliveryMode.NON_PERSISTENT, 8, 1000*60*10L);
			
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//
		
		
		
	}
	
	
	
	public void send2(){
		
		try {
			Destination d2 = this.session.createQueue("first");
			
			TextMessage msg = this.session.createTextMessage("������һ��TextMessage");
			
			this.messageProducer.send(d2, msg, DeliveryMode.NON_PERSISTENT, 1, 1000*10*60L);
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		
		Producer p = new Producer();
		p.send1();
		//p.send2();
		
	}
	
	
	
}