package com.java.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4J {

	public static void main(String[] args) {

	
		Logger logger = LoggerFactory.getLogger(Log4J.class);  
		
		logger.info("上海");
	
	}

}
