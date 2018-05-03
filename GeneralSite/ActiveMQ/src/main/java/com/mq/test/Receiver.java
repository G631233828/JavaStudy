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

		// 第一步，建立ConnectionFactory工厂对象，需要填入用户名，密码，以及链接地址，均使用默认即可，默认端口为：tcp://localhost:61616
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin",
				"admin", "tcp://121.40.57.56:61616");

		// 第二步，通过ConnectionFactory工程对象我们创建一个Connection连接，并且调用Connection的start方法开启链接，Connection默认是关闭的
		Connection connection = connectionFactory.createConnection();
		connection.start();// 开启

		// 第三步，通过connection对象创建session会话（上下文环境对象），用于接收消息，参数配置1为是否启用事务，参数配置2为签收模式，一般设置自动签收
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);//自动签收模式
		//Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);//客户端必须签收

		//第四步，通过Session创建Destination对象，指的是一个客户端用来指定生产消息目标和消费消息来源的对象，在PTP模式中，Destination被称作Queue即队列
		Destination destination = session.createQueue("first");
				
		//第五步，通过session创建MessageConsumer
		MessageConsumer consumer = session.createConsumer(destination);
		
		while(true){
			
			TextMessage msg = (TextMessage) consumer.receive();
			
			//msg.acknowledge();//手动签收 在Session.CLIENT_ACKNOWLEDGE模式下必须手动签收一下
			
			System.out.println("消费数据："+msg.getText());
		}
		
				
		
		
	}

}
