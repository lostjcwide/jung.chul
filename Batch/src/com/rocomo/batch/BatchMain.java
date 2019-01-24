package com.rocomo.batch;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.rocomo.mapper.Mapper;

public class BatchMain {
	
	@SuppressWarnings({ "resource", "unchecked" })
	public static void main(String[] args) {
		String[] paths = {"root-context.xml", "mybatis-context.xml", "redis-context.xml"};
		
		ApplicationContext context = new ClassPathXmlApplicationContext(paths);
		DummyBean bean = (DummyBean) context.getBean("dummy");
		
		RedisTemplate<String, String> redisTemplate = (RedisTemplate<String, String>) context.getBean("redisTemplate");
		ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
		ListOperations<String, String> listOps = redisTemplate.opsForList();
		
		SqlSessionTemplate sqlSession = (SqlSessionTemplate) context.getBean("sqlSession");
		Mapper mapper = sqlSession.getMapper(Mapper.class);
		
		try {
			System.out.println( sqlSession.getMapper(Mapper.class).select1().toString() );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
