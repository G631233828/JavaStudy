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
		// 第一步，建立ConnectionFactory工厂对象，需要填入用户名，密码，以及要连接的地址，均使用默认的即可，默认端口：“tcp://localhost:616161”
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin",
				"admin", "tcp://localhost:61616");
		// 第二步，简历ConnectionFactory工厂对象我们创建一个connection链接，并且调用Connection的start方法开启链接，Connection默认是关闭的
		Connection connection = connectionFactory.createConnection();
		connection.start();
		
		//第三步，通过connection对象创建session会话（上下文环境对象），用于接受消息，参数配置1为是否启用事务，参数配置2为签收模式，一般我们设置自动签收
		//自动签收模式 Boolean.TRUE 为支持事务提交
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		//Session session = connection.createSession(Boolean.TRUE, Session.CLIENT_ACKNOWLEDGE);//客户端必须签收
		
		
		//第四步，通过Session 创建destination对象，指的是客户端用来指定生产消息目标和消费消息来源的对象，在PTP模式中，Destination被称作
		Destination destination = session.createQueue("first");
		
		//第五步，我们需要通过Session对象创建消息的发送和接收对象（生产者和消费者）MessageProducer/MessageConsumer
		MessageProducer producer = session.createProducer(null);
		
		//第六步，我们可以使用MessageProducer的setDeliveryMode方法为其设置持久化特性和非持久化特性（DeliveryMode）
		//producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);//非持久化
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

		//第七步，最后我们使用JMS规范的TextMessage形式创建数据（通过Session对象），并用MessageProduce r的send方法发送数据，同理客户端用receive方法获取数据
		for(int i =1;i<10;i++){
			TextMessage msg = session.createTextMessage("我是消息内容"+i);
			//producer.send(destination,msg);
			//第一个参数目标地址
			//第二个参数具体的数据信息
			//第三个参数传达数据的模式（持久化和非持久化）
			//第四个参数优先级
			//第五个参数过期时间
			  producer.send(destination, msg, DeliveryMode.PERSISTENT, i, 1000*20);//i是优先级，20000存活时间
		}
		TimeUnit.SECONDS.sleep(3);
		
		//提交事务，整体提交数据
		session.commit();
		
		
		if(connection!=null){
			connection.close();
		}
		
		
	}
}
