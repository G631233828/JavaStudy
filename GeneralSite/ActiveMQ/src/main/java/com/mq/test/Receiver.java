package com.mq.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {

	public static void main(String[] args) throws JMSException {

		// ��һ��������ConnectionFactory����������Ҫ�����û��������룬�Լ����ӵ�ַ����ʹ��Ĭ�ϼ��ɣ�Ĭ�϶˿�Ϊ��tcp://localhost:61616
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin",
				"admin", "tcp://localhost:61616");

		// �ڶ�����ͨ��ConnectionFactory���̶������Ǵ���һ��Connection���ӣ����ҵ���Connection��start�����������ӣ�ConnectionĬ���ǹرյ�
		Connection connection = connectionFactory.createConnection();
		connection.start();// ����

		// ��������ͨ��connection���󴴽�session�Ự�������Ļ������󣩣����ڽ�����Ϣ����������1Ϊ�Ƿ��������񣬲�������2Ϊǩ��ģʽ��һ�������Զ�ǩ��
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);//�Զ�ǩ��ģʽ
		//Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);//�ͻ��˱���ǩ��

		//���Ĳ���ͨ��Session����Destination����ָ����һ���ͻ�������ָ��������ϢĿ���������Ϣ��Դ�Ķ�����PTPģʽ�У�Destination������Queue������
		Destination destination = session.createQueue("first");
				
		//���岽��ͨ��session����MessageConsumer
		MessageConsumer consumer = session.createConsumer(destination);
		
		while(true){
			
			TextMessage msg = (TextMessage) consumer.receive();
			
			//msg.acknowledge();//�ֶ�ǩ�� ��Session.CLIENT_ACKNOWLEDGEģʽ�±����ֶ�ǩ��һ��
			
			System.out.println("�������ݣ�"+msg.getText());
		}
		
				
		
		
	}

}