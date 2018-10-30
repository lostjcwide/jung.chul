package com;

import java.util.ArrayList;
import java.util.List;

public class WsMain {
	
	public static void main(String[] args) {
		List<String> urls = new ArrayList<String>();
		
		
		for (String url : urls) {
			WsClient.call(url, "{\"method\":\"shh_getPrivateKey\",\"id\":\"1540884195933\",\"jsonrpc\":\"2.0\",\"params\":[\"0x1286c3e756113b569921383d0679ae17284b9ec065d4c9574f0358f3778e0f1b\"]}");
		}
		
	}
	

}
