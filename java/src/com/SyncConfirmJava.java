package com;

import org.json.JSONObject;

public class SyncConfirmJava {
	
	public static void main(String[] args) {
		String[] urls = {
					"http://ec2-13-209-44-18.ap-northeast-2.compute.amazonaws.com:9540"
					, "http://ec2-13-124-129-126.ap-northeast-2.compute.amazonaws.com:9540"
				};
		
		for (String url : urls) {
			JSONObject result = JsonCall.call(url);
			System.out.println(Long.parseLong(result.getString("result").replace("0x", ""), 16));
//			String blockNumber = result.getString("result");
//			System.out.println(blockNumber);
		}
		
	}
}
