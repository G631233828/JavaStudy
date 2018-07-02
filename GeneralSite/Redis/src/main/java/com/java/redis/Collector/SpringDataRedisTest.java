package com.java.redis.Collector;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redis.framework.RedisService;
import org.redis.framework.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-context.xml")
public class SpringDataRedisTest {
	
	
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private RedisServiceImpl redisService ;
	@Test
	public void test1(){
		//redisTemplate.opsForValue().set("studentname", "jay");
		
		String name = (String) redisTemplate.opsForValue().get("age");
		
		System.out.println(name);
	}

	/**
	 * Redis 事务 
	 */
	@Test
	public void test2(){
		//开启一个事务
		@SuppressWarnings({ "unchecked", "unused" })
		List<Object> txResults = (List<Object>) this.redisTemplate.execute(new SessionCallback<List<Object>>(){

			@SuppressWarnings("rawtypes")
			public List<Object> execute(RedisOperations operations) throws DataAccessException {
				
				operations.multi();
				operations.opsForSet().add("money", 2000);
				return operations.exec();
			}
		});
		System.out.println(txResults.get(0));
		
	}
	

	@Test
	public void test3(){
		this.redisService.set("age","13");
	}
	
	@Test
	public void test4(){
		
		long size =this.redisService.dbSize();
		System.out.println(size);
	}


}
