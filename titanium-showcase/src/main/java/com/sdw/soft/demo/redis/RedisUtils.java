/** @Date 下午3:01:34
  * @version 1.0.0
  * @author shangyd
  * Copyright (c) 2015
  */
package com.sdw.soft.demo.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * redis 工具类
 */
public class RedisUtils {

	private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);
	
	private static Map<String,JedisPool> maps = new HashMap<String,JedisPool>();
		
	private RedisUtils(){
		
	}
	
	
	private static class RedisHolder{
		private static RedisUtils utils = new RedisUtils();
	}
	
	public static JedisPool getPool(String ip,int port){
		String key = ip+":"+port;
		JedisPool pool = null;
		if(!maps.containsKey(key)){
			JedisPoolConfig config = new JedisPoolConfig();
			ResourceBundle bundle = ResourceBundle.getBundle("redis");
			config.setMaxActive(Integer.valueOf(bundle.getString("redis.maxActive")));
			config.setMaxIdle(Integer.valueOf(bundle.getString("redis.maxIdle")));
			config.setMaxWait(Integer.valueOf(bundle.getString("redis.maxWait")));
			config.setTestOnBorrow(true);
			config.setTestOnReturn(true);
			
			pool = new JedisPool(config, ip, port);
		}else{
			 pool = maps.get(key);
		}
		return pool;
	}

	public static RedisUtils getInstance(){
		return RedisHolder.utils;
	}
	public Jedis getJedis(String ip,int port){
		Jedis jedis = null;
		try {
			jedis = getPool(ip, port).getResource();
		} catch (Exception e) {
			getPool(ip, port).returnBrokenResource(jedis);
			e.printStackTrace();
		}
		return jedis;
	}
	
	public void closeJedis(Jedis jedis,String ip,int port){
		if(jedis != null){
			getPool(ip, port).returnResource(jedis);
		}
	}
}
