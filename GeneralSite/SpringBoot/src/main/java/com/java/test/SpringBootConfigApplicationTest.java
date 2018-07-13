package com.java.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.pojo.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication
@ComponentScan(basePackages = {"com.java.*"})
//@ImportResource(locations={"classpath:beans.xml"})//导入Spring的配置文件springBoot不推荐使用xml配置文件
public class SpringBootConfigApplicationTest {

	
	@Autowired
	Person pserson;

	@Autowired
	ApplicationContext ioc;
	
	@Test
	public void testHelloService(){
		boolean b = ioc.containsBean("helloService");
		System.out.println(b);
	}
	
	
	
	@Test
	public void contextLoads() {

		System.out.println(pserson);
	}

}
