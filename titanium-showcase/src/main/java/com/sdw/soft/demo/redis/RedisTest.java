/** @Date 下午4:31:48
  * @version 1.0.0
  * @author shangyd
  * Copyright (c) 2015
  */
package com.sdw.soft.demo.redis;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisTest {
	
	private Jedis jedis = null;
	
	@Before
	public void setup(){
		jedis = RedisUtils.getInstance().getJedis("127.0.0.1", 6379);
	}

	@Test
	public void test01(){
		jedis.set("foo", "bar");
		jedis.append("foo", "fpp");//在原来value的基础上append操作
		System.out.println(jedis.get("foo"));
		jedis.set("foo", "bug");//覆盖原来的value
		jedis.del("foo");//删除key对应记录
		System.out.println(jedis.get("foo"));
		boolean exists = jedis.exists("foo");//判断key是否存在
		System.out.println(exists);
		jedis.mset("name","foo","age","23");
		System.out.println(jedis.mget("name","age"));
		
		System.out.println(jedis.dbSize());
	}
}