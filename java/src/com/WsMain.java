package com;

import java.util.ArrayList;
import java.util.List;

public class WsMain {
	
	public static void main(String[] args) {
		List<String> urls = new ArrayList<String>();
		
		for (String url : urls) {
			WsClient.call(url);
		}
		
	}
	

}
