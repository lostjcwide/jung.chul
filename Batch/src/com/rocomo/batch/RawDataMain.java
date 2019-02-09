package com.rocomo.batch;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.rocomo.mapper.Mapper;
import com.rocomo.utils.RedisUtil;
import com.rocomo.vo.RawVo;

public class RawDataMain {
	
	@SuppressWarnings({ "resource" })
	public static void main(String[] args) {
//		Type type = new TypeToken<Map<String, String>>(){}.getType();
//		String[] paths = {"root-context.xml", "mybatis-context.xml", "redis-context.xml", "quartz-context.xml"};
		String[] paths = {"root-context.xml", "mybatis-context.xml", "redis-context.xml"};
		
		ApplicationContext context = new ClassPathXmlApplicationContext(paths);
		
		SqlSessionTemplate sqlSession = (SqlSessionTemplate) context.getBean("sqlSession");
		RedisUtil util = (RedisUtil) context.getBean("redisUtil");
		
		Mapper mapper = sqlSession.getMapper(Mapper.class);
		
		try {
			
			TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	    	Calendar c = Calendar.getInstance(TimeZone.getDefault());
	    	c.add(Calendar.DATE, -4);
	    	Date dt = c.getTime();
	    	
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    	String yyyyMMdd = sdf.format(dt);
	    	
	    	System.out.println(yyyyMMdd);
	    	
	    	String[] exchanges = {"binance", "bittrex", "cryptopia", "hitbtc", "kucoin", "yobit"};
	    	
	    	for (String exchange : exchanges) {
	    		String pattern = "mp:"+exchange+":"+yyyyMMdd+":raw:*";
	    		
	    		Set<String> keys = util.getKeys(pattern);
	    		
	    		if( keys.size() > 0 ) {
	    			List<String> values = util.multiGet(keys);
	    			for (String value : values) {
	    				RawVo rawVo = new Gson().fromJson(value, RawVo.class);
	    				if( null != rawVo ) {
	    					String[] arr = rawVo.getPair().split("/");
	    					rawVo.setCoin(arr[0]);
	    					rawVo.setMarket(arr[1]);
	    					rawVo.setExchange(exchange);
	    					rawVo.setRegDttm(yyyyMMdd);
	    					
	    					mapper.insertRawData(rawVo);
	    				}
	    				
	    			}
	    			
	    			util.deleteKeys(keys);
	    		}
			}
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
