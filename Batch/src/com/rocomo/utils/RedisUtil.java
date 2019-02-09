package com.rocomo.utils;

import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ValueOperations;

public class RedisUtil {
	private ValueOperations<String, String> valueOperations;
	
	private RedisTemplate<String, String> redisTemplate;
	
	public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	public void setValue(String key, String value) {
		valueOperations.set(key, value);
	}

	public String getValue(String key) {
		return valueOperations.get(key);
	}
	
	public Set<String> getKeys(String pattern) {
		RedisConnection redisConnection = null;
		try {
			redisConnection = redisTemplate.getConnectionFactory().getConnection();
		    ScanOptions options = ScanOptions
		    		.scanOptions()
		    		.match(pattern)
//		    		.count(10)
		    		.build();
		    Cursor<byte[]> cursor = redisConnection.scan(options);
		    while (cursor.hasNext()) {
		        System.out.println(new String(cursor.next()));
		    }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			redisConnection.close();
		}
		return null;
//		return valueOperations.getOperations().keys(pattern);
    }
	
	public List<String> multiGet(Set<String> keys) throws Exception{
		return valueOperations.multiGet(keys);
	}
	
	public void deleteKeys(Set<String> keys) throws Exception{
		valueOperations.getOperations().delete(keys);
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			String[] paths = {"redis-context.xml"};
			ApplicationContext context = new ClassPathXmlApplicationContext(paths);
			RedisUtil util = (RedisUtil) context.getBean("redisUtil");
			
			util.getKeys("mp:*");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}