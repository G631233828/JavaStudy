package com.java.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author fliay
 * @EnableAutoConfiguration 来标注一个主程序 说明是一个springboot应用
 *
 */

@RestController
@EnableAutoConfiguration
public class Example {

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	public static void main(String[] args) throws Exception {
		//spring应用启动
		SpringApplication.run(Example.class, args);
	}

}