package com.rocomo.utils;

import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.ValueOperations;

public class RedisUtil {
	private ValueOperations<String, String> valueOperations;
	
	public RedisUtil() {
		
	}
	
	public RedisUtil(ValueOperations<String, String> valueOps) {
		this.valueOperations = valueOps;
	}

	public void setValue(String key, String value) {
		valueOperations.set(key, value);
	}

	public String getValue(String key) {
		return valueOperations.get(key);
	}
	
	public Set<String> getKeys(String pattern) {
		return valueOperations.getOperations().keys(pattern);
    }
	
	public List<String> multiGet(Set<String> keys) throws Exception{
		return valueOperations.multiGet(keys);
	}
	
	public void deleteKeys(Set<String> keys) throws Exception{
		valueOperations.getOperations().delete(keys);
	}
	
}