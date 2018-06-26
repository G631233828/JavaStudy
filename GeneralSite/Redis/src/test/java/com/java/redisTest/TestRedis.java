package com.java.redisTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class TestRedis {
	//单独连接一台机器
	private static Jedis jedis; 
	//主从，哨兵 使用
	private static ShardedJedis shard;
	//连接池
	private static ShardedJedisPool pool;

	@BeforeClass
	public static void setUpBeforeClass() {
		// 创建单个节点
		jedis = new Jedis("192.168.1.149", 6379);
		
		// 设置密码
		jedis.auth("123456");
		// 分片
		List<JedisShardInfo> shards = Arrays.asList(new JedisShardInfo("192.168.1.159", 6379));
		shard = new ShardedJedis(shards);
		GenericObjectPoolConfig goConfig = new GenericObjectPoolConfig();
		goConfig.setMaxTotal(100);
		goConfig.setMaxIdle(20);
		goConfig.setMaxWaitMillis(-1);
		goConfig.setTestOnBorrow(true);
		pool = new ShardedJedisPool(goConfig, shards);
	}
	

	@Test
	public void test1() {
		String a = jedis.get("aaa");
		System.out.println(a);
	}
	
	
	@Test
	public void testMap(){
		//添加数据
		Map<String,String> map = new HashMap<>();
		map.put("a", "a");
		map.put("b", "b");
		map.put("c", "c");
		jedis.hmset("map1", map);
		//取出map1中的name执行结果：[minxr]->注意结果是一个泛型的list
		//第一个参数是存入redis中map对象的key，后面的是放入map中的对象key，后面的key可以跟多个，是可变参数
		List<String> rsmap = jedis.hmget("map1","a", "b","c");
		System.out.println(rsmap);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@AfterClass
	public static void tearDownAfterClass(){
		jedis.disconnect();
		shard.disconnect();
		pool.destroy();
	}
	
	
	
	
	
	
	
	
	

}
