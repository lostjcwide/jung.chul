package com.rocomo.utils;

import org.springframework.data.redis.core.ValueOperations;

public class RedisUtil {
	
	private ValueOperations<String, String> valueOps;
	
	public RedisUtil() {
		
	}
	
	public RedisUtil(ValueOperations<String, String> valueOps) {
		this.valueOps = valueOps;
	}

	public void setValue(String key, String value) {
		valueOps.set(key, value);
	}

	public String getValue(String key) {
		return valueOps.get(key);
	}

}