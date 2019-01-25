package com.rocomo.batch;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rocomo.mapper.Mapper;
import com.rocomo.utils.RedisUtil;

public class BatchMain {
	
	@SuppressWarnings({ "resource" })
	public static void main(String[] args) {
		String[] paths = {"root-context.xml", "mybatis-context.xml", "redis-context.xml"};
		
		ApplicationContext context = new ClassPathXmlApplicationContext(paths);
		
		SqlSessionTemplate sqlSession = (SqlSessionTemplate) context.getBean("sqlSession");
		RedisUtil util = (RedisUtil) context.getBean("redisUtil");
		
		Mapper mapper = sqlSession.getMapper(Mapper.class);
		
		try {
			System.out.println( sqlSession.getMapper(Mapper.class).select1().toString() );
			System.out.println( util.getValue("mp:binance:20190123:raw:200002/200003") );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
