package com.java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.java.service.HelloService;

/**
 * @Configuration:指明当前类是一个配置类；就是用来替代之前的Spring配置文件
 * @author fliay 在配置文件中使用<bean></bean>标签添加组件
 */
@Configuration
public class MyAppConfig {

	/**
	 * 将方法的返回值添加到容器中；容器中这个组建默认的id就是方法名
	 * 
	 * @return
	 */
	@Bean
	public HelloService hellService() {
		System.out.println("添加helloService主键");
		return new HelloService();
	}

}
