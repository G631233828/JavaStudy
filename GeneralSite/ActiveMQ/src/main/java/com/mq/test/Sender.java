package com.mq.test;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {
	public static void main(String[] args) throws JMSException, InterruptedException {
		// ��һ��������ConnectionFactory����������Ҫ�����û��������룬�Լ�Ҫ���ӵĵ�ַ����ʹ��Ĭ�ϵļ��ɣ�Ĭ�϶˿ڣ���tcp://localhost:616161��
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin",
				"admin", "tcp://localhost:61616");
		// �ڶ���������ConnectionFactory�����������Ǵ���һ��connection���ӣ����ҵ���Connection��start�����������ӣ�ConnectionĬ���ǹرյ�
		Connection connection = connectionFactory.createConnection();
		connection.start();
		
		//��������ͨ��connection���󴴽�session�Ự�������Ļ������󣩣����ڽ�����Ϣ����������1Ϊ�Ƿ��������񣬲�������2Ϊǩ��ģʽ��һ�����������Զ�ǩ��
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);//�Զ�ǩ��ģʽ Boolean.TRUE Ϊ֧�������ύ
		//Session session = connection.createSession(Boolean.TRUE, Session.CLIENT_ACKNOWLEDGE);//�ͻ��˱���ǩ��
		
		
		//���Ĳ���ͨ��Session ����destination����ָ���ǿͻ�������ָ��������ϢĿ���������Ϣ��Դ�Ķ�����PTPģʽ�У�Destination������
		Destination destination = session.createQueue("first");
		
		//���岽��������Ҫͨ��Session���󴴽���Ϣ�ķ��ͺͽ��ն��������ߺ������ߣ�MessageProducer/MessageConsumer
		MessageProducer producer = session.createProducer(null);
		
		//�����������ǿ���ʹ��MessageProducer��setDeliveryMode����Ϊ�����ó־û����Ժͷǳ־û����ԣ�DeliveryMode��
		//producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);//�ǳ־û�
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

		//���߲����������ʹ��JMS�淶��TextMessage��ʽ�������ݣ�ͨ��Session���󣩣�����MessageProduce r��send�����������ݣ�ͬ���ͻ�����receive������ȡ����
		for(int i =1;i<10;i++){
			TextMessage msg = session.createTextMessage("������Ϣ����"+i);
			//producer.send(destination,msg);
			  producer.send(destination, msg, DeliveryMode.PERSISTENT, i, 1000*20);//i�����ȼ���20000���ʱ��
		}
		TimeUnit.SECONDS.sleep(3);
		
		//�ύ���������ύ����
		session.commit();
		
		
		if(connection!=null){
			connection.close();
		}
		
		
	}
}