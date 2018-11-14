package com;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		
		System.out.println(!"/**/*".equals("/wallet/mypage/setNewlPwdInputForm*"));
		
		List<String> sl = FileJava.getFileConList("D:/data/json.txt");
		
		for (String json : sl) {
			try {
				System.out.println(json);
				JedisUtil.lPush("180:19", json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
