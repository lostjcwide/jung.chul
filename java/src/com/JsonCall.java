package com;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonCall {
	
	public static JSONObject call(String url){
		JSONObject jsonObject = new JSONObject();
		try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");

            OutputStream os = conn.getOutputStream();
            JSONObject json = new JSONObject();
            json.put("method", "eth_blockNumber");
            JSONArray array = new JSONArray();
            json.put("params", array);
            json.put("id", 1);
            json.put("jsonrpc", "2.0");
            os.write(json.toString().getBytes());
            os.close();

            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils	.toString(in, "UTF-8");
            jsonObject = new JSONObject(result);

            in.close();
            conn.disconnect();
            

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
}
