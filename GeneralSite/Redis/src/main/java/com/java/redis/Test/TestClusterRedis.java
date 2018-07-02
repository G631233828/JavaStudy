package com.java.redis.Test;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;


public class TestClusterRedis {
	
	public static void main(String[] args) {
		
		Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
		jedisClusterNode.add(new HostAndPort("192.168.1.149", 7001));
		jedisClusterNode.add(new HostAndPort("192.168.1.149", 7002));
		jedisClusterNode.add(new HostAndPort("192.168.1.149", 7003));
		jedisClusterNode.add(new HostAndPort("192.168.1.149", 7004));
		jedisClusterNode.add(new HostAndPort("192.168.1.149", 7005));
		jedisClusterNode.add(new HostAndPort("192.168.1.149", 7006));
		
		JedisPoolConfig cfg = new JedisPoolConfig();
		cfg.setMaxTotal(100);
		cfg.setMaxIdle(20);
		cfg.setMaxWaitMillis(-1);
		cfg.setTestOnBorrow(true);
		JedisCluster jc = new JedisCluster(jedisClusterNode,6000,100,cfg);
		
		System.out.println(jc.set("age", "20"));
		System.out.println(jc.get("age"));
		
	}
	
	

}
