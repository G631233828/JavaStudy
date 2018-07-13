package com.java.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

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
	

	public static void main(String[] args) throws Exception {
		//spring应用启动
		SpringApplication.run(Example.class, args);
	}

}