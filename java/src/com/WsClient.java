package com;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONObject;

public class WsClient {
	public static void call(String url){
		try {
			// https://websocket.org/echo.html가 제공하는 WebSocket 에코 서버에서 기능 테스트
			WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://"+url), new Draft_10()) {

			    @Override
			    public void onOpen(ServerHandshake serverHandshake) {
			        // WebSocket 서버 연결 후 동작 정의, 아래는 Hello 메시지 발송
			    	JSONObject json = new JSONObject();
		            json.put("method", "eth_blockNumber");
		            JSONArray array = new JSONArray();
		            json.put("params", array);
		            json.put("id", 1);
		            json.put("jsonrpc", "2.0");
		            
			        this.send(json.toString().getBytes());
			    }

			    @Override
			    public void onMessage(String message) {
			        // WebSocket 서버에서 메시지 수신시 동작 정의, 아래는 Hello 메시지 수신시 연결 종료
//			        System.out.println(message);
			        JSONObject js = new JSONObject(message);
			        System.out.println(url + " : " + Long.parseLong(js.getString("result").replace("0x", ""), 16));
			    }

			    @Override
			    public void onClose(int code, String reason, boolean remote) {
			        // 서버 연결 종료 후 동작 정의
			    }

			    @Override
			    public void onError(Exception ex) {
			        // 예외 발생시 동작 정의
			    	ex.printStackTrace();
			    }

			};
			// 앞서 정의한 WebSocket 서버에 연결한다.
			webSocketClient.connect();
			
			Thread.sleep(3000);
			
			webSocketClient.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void call(String url, String jsonStr){
		try {
			// https://websocket.org/echo.html가 제공하는 WebSocket 에코 서버에서 기능 테스트
			WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://"+url), new Draft_10()) {

			    @Override
			    public void onOpen(ServerHandshake serverHandshake) {
			        // WebSocket 서버 연결 후 동작 정의, 아래는 Hello 메시지 발송
			    	JSONObject json = new JSONObject(jsonStr);
			        this.send(json.toString().getBytes());
			    }

			    @Override
			    public void onMessage(String message) {
			        // WebSocket 서버에서 메시지 수신시 동작 정의, 아래는 Hello 메시지 수신시 연결 종료
//			        System.out.println(message);
			        JSONObject js = new JSONObject(message);
			        System.out.println(url + " : "+js.toString());
//			        System.out.println(url + " : " + Long.parseLong(js.getString("result").replace("0x", ""), 16));
			    }

			    @Override
			    public void onClose(int code, String reason, boolean remote) {
			        // 서버 연결 종료 후 동작 정의
			    }

			    @Override
			    public void onError(Exception ex) {
			        // 예외 발생시 동작 정의
			    	ex.printStackTrace();
			    }

			};
			// 앞서 정의한 WebSocket 서버에 연결한다.
			webSocketClient.connect();
			
			Thread.sleep(3000);
			
			webSocketClient.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
