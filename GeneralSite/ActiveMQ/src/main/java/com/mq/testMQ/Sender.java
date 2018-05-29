package com.mq.testMQ;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import net.sf.json.JSONObject;

public class Sender {
	public static void main(String[] args) throws JMSException, InterruptedException {
		// ��һ��������ConnectionFactory����������Ҫ�����û��������룬�Լ�Ҫ���ӵĵ�ַ����ʹ��Ĭ�ϵļ��ɣ�Ĭ�϶˿ڣ���tcp://localhost:616161��
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin",
					"admin", "tcp://121.40.57.56:61616");
		// �ڶ���������ConnectionFactory�����������Ǵ���һ��connection���ӣ����ҵ���Connection��start�����������ӣ�ConnectionĬ���ǹرյ�
		Connection connection = connectionFactory.createConnection();
		connection.start();
		
		//��������ͨ��connection���󴴽�session�Ự�������Ļ������󣩣����ڽ�����Ϣ����������1Ϊ�Ƿ��������񣬲�������2Ϊǩ��ģʽ��һ�����������Զ�ǩ��
		//�Զ�ǩ��ģʽ Boolean.TRUE Ϊ֧�������ύ
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		//Session session = connection.createSession(Boolean.TRUE, Session.CLIENT_ACKNOWLEDGE);//�ͻ��˱���ǩ��
		
		//���Ĳ���ͨ��Session ����destination����ָ���ǿͻ�������ָ��������ϢĿ���������Ϣ��Դ�Ķ�����PTPģʽ�У�Destination������
		Destination destination = session.createQueue("test123");
		
		//���岽��������Ҫͨ��Session���󴴽���Ϣ�ķ��ͺͽ��ն��������ߺ������ߣ�MessageProducer/MessageConsumer
		MessageProducer producer = session.createProducer(null);
		
		//�����������ǿ���ʹ��MessageProducer��setDeliveryMode����Ϊ�����ó־û����Ժͷǳ־û����ԣ�DeliveryMode��
		//producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);//�ǳ־û�
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

		//���߲����������ʹ��JMS�淶��TextMessage��ʽ�������ݣ�ͨ��Session���󣩣�����MessageProduce r��send�����������ݣ�ͬ���ͻ�����receive������ȡ����
       
    	teacher s = new teacher();
		s.setDateTime("2018-04-22 23:17:29");
        s.setName("fliay");
        s.setRemark("��У֪ͨ");
        s.setSchoolAddress("�Ϻ��ֶ�");
        s.setTitle("��ȫ�ﵽѧУ");
        s.setWeChatToken("ooiMKv7cqR-2EgkeC9LdATpr-mbY");
		
		JSONObject json = JSONObject.fromObject(s);
      
      	System.out.println(json.toString());
        
        TextMessage msg = session.createTextMessage(json.toString());
        producer.send(destination, msg, DeliveryMode.NON_PERSISTENT,1, 1000*20);//i�����ȼ���20000���ʱ��
    	
		
		//�ύ���������ύ����
		session.commit();
		
		
		if(connection!=null){
			connection.close();
		}
		
		
	}
}










