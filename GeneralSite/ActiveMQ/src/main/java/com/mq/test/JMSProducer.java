package com.mq.test;
 
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
 
import org.apache.activemq.ActiveMQConnectionFactory;
 
/**
 * ��Ϣ�������ߣ������ߣ�
 * 
 * @author Administrator
 * 
 */
public class JMSProducer {
 
 
    public static void main(String[] args) {
        try {
            //��һ��������ConnectionFactory����������Ҫ�����û��������롢�Լ�Ҫ���ӵĵ�ַ����ʹ��Ĭ�ϼ��ɣ�Ĭ�϶˿�Ϊ"tcp://localhost:61616"
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin",  "admin",  "failover:(tcp://localhost:61616)?Randomize=false");
 
            //�ڶ�����ͨ��ConnectionFactory�����������Ǵ���һ��Connection���ӣ����ҵ���Connection��start�����������ӣ�ConnectionĬ���ǹرյġ�
            Connection connection = connectionFactory.createConnection();
            connection.start();
 
            //��������ͨ��Connection���󴴽�Session�Ự�������Ļ������󣩣����ڽ�����Ϣ����������1Ϊ�Ƿ����������񣬲�������2Ϊǩ��ģʽ��һ�����������Զ�ǩ�ա�
            Session session = connection.createSession(Boolean.TRUE, Session.CLIENT_ACKNOWLEDGE);
 
            //���Ĳ���ͨ��Session����Destination����ָ����һ���ͻ�������ָ��������ϢĿ���������Ϣ��Դ�Ķ�����PTPģʽ�У�Destination������Queue�����У���Pub/Subģʽ��Destination������Topic�����⡣�ڳ����п���ʹ�ö��Queue��Topic��
            Destination destination = session.createQueue("HelloWorld");
 
            //���岽��������Ҫͨ��Session���󴴽���Ϣ�ķ��ͺͽ��ն��������ߺ������ߣ�MessageProducer/MessageConsumer��
            MessageProducer producer = session.createProducer(null);
 
            //�����������ǿ���ʹ��MessageProducer��setDeliveryMode����Ϊ�����ó־û����Ժͷǳ־û����ԣ�DeliveryMode���������Ժ���ϸ���ܡ�
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
 
            //���߲����������ʹ��JMS�淶��TextMessage��ʽ�������ݣ�ͨ��Session���󣩣�����MessageProducer��send�����������ݡ�ͬ���ͻ���ʹ��receive�������н������ݡ����Ҫ���ǹر�Connection���ӡ�
 
            for(int i = 0 ; i < 1 ; i ++){
                TextMessage msg = session.createTextMessage("������Ϣ����" + i);
                // ��һ������Ŀ���ַ
                // �ڶ������� �����������Ϣ
                // ���������� �������ݵ�ģʽ
                // ���ĸ����� ���ȼ�
                // ��������� ��Ϣ�Ĺ���ʱ��
                producer.send(destination, msg, DeliveryMode.PERSISTENT, 0 , 1000L*60);
                System.out.println("������Ϣ��" + msg.getText());
                session.commit(); //��������ʱ�ǵ��ύ���񣬲�Ȼ���Ѷ˽��ղ�����Ϣ
                Thread.sleep(1000);
            }
 
            if(connection != null){
                connection.close();
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
 
}