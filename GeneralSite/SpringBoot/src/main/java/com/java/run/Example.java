package com.java.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.pojo.Person;

/**
 * 
 * @author fliay
 * @EnableAutoConfiguration 来标注一个主程序 说明是一个springboot应用
 *
 */

@RestController
//@EnableAutoConfiguration
@SpringBootApplication
//改变自动扫描的包
@ComponentScan(basePackages = {"com.java.*"})
public class Example {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	Person person;

	@RequestMapping("/")
	Person home() {
		logger.info(person.toString());
		return person;
	}

	public static void main(String[] args) throws Exception {
		//spring应用启动
		SpringApplication.run(Example.class, args);
	}

}