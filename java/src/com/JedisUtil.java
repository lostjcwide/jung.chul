package com;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {
	
	public static void lPush(String key, String data) throws Exception{
		String host = "";
        int port = 6479;
        int timeout = 3000;
        int db = 0;
        
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        
        JedisPool pool = new JedisPool(jedisPoolConfig, host, port, timeout, null, db);
        
        Jedis jedis = pool.getResource();
        
        System.out.println(jedis.isConnected());
        
        jedis.lpush(key, data);
		
		try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
        if( jedis != null ){
            jedis.close();
        }
        pool.close();
		
	}
	
}
