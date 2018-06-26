package com.java.redis;

import redis.clients.jedis.Jedis;

public class TestRedis {
	
	public static void main(String[] args) {
		Jedis j = new Jedis("192.168.1.149", 6379);
		j.auth("123456");
		String a =j.get("aaa");
		System.out.println(a);
	}

}
