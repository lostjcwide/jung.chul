package com;

import org.json.JSONObject;

public class SyncConfirmJava {
	
	public static void main(String[] args) {
		String[] urls = {
				};
		
		for (String url : urls) {
			JSONObject result = JsonCall.call(url);
			String blockNumber = result.getString("result");
			System.out.println(blockNumber);
		}
		
	}
}
