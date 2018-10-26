package com;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisMain {
	
	
	
	public static void main(String[] args) {
		String host = "";
        int port = 6379;
        int timeout = 3000;
        int db = 0;
        
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        
        JedisPool pool = new JedisPool(jedisPoolConfig, host, port, timeout, null, db);
        
        Jedis jedis = pool.getResource();
        
        //Connect 체크 
        System.out.println(jedis.isConnected());
        
        jedis.set("test:test", "1");
        
        System.out.println( jedis.get("test:test") );
        
//        String key = "99:98";
        
//        jedis.lpush(key, "1");
        
//        jedis.set("key4", "6");
//        jedis.set("key5", "6");
//        
//        // 데이터의 만료시간을 지정
//        jedis.expire("key5",1);
        
//        String key = "99:98";
        
//        int i = 0;
//        while( true ){
//        	i++;
//        	jedis.lpush(key, String.valueOf(i));
//        	
//        	if( i == 1000 ){
//        		break;
//        	}
//        }
        
//        jedis.lpush(key, String.valueOf(4));
        
//        System.out.println( jedis.rpop(key) );
        
//        String val = "";
//        i = 0;
//        while( null != (val = jedis.rpop(key)) ){
//        	i++;
//        	System.out.println(i);
//        	System.out.println(val);
//        }
        
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
