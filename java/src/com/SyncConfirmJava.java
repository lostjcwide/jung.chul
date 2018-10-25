package com;

import org.json.JSONObject;

public class SyncConfirmJava {
	
	public static void main(String[] args) {
		String[] urls = {
				"http://ec2-13-209-57-181.ap-northeast-2.compute.amazonaws.com:8540"
				, "http://ec2-52-78-165-106.ap-northeast-2.compute.amazonaws.com:8540"
				, "http://ec2-52-79-36-191.ap-northeast-2.compute.amazonaws.com:8540"
				, "http://ec2-52-78-205-130.ap-northeast-2.compute.amazonaws.com:8540"
				};
		
		for (String url : urls) {
			JSONObject result = JsonCall.call(url);
			String blockNumber = result.getString("result");
			System.out.println(blockNumber);
		}
		
	}
}
