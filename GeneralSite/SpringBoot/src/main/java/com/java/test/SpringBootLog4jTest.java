package com.java.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringBootLog4jTest {

	//记录器
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Test
	public void testContextLoads(){
		//日志的级别
		//日志的级别由低到高 trace<debuf<info<warn<error
		//是可以调整输出的日志级别，日志就只会在这个级别以后的高级级别生效
		logger.trace("这是trace日志");
		logger.debug("这是debug日志");
		logger.info("这是info日志");
		logger.warn("这是warn日志");
		logger.error("这是error日志");
	}
}
