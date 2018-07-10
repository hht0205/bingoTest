package com.hxxc;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.dom4j.Document;

import com.hxxc.encrypt.DES;
import com.hxxc.entity.ResponseEntity;

import net.sf.json.JSONObject;

public class DoMethod {
	
	public static ResponseEntity doSome(String data,boolean isJsonResult){
		HttpURLConnection urlConnection=null;
		BufferedOutputStream bufferedOutputStream = null;
		BufferedInputStream bufferedInputStream = null;
		URL url;
		ResponseEntity responseEntity = null;
		try {
			url = new URL("http://127.0.0.1:8080/bingo/bingoServlet");   
			urlConnection = (HttpURLConnection)url.openConnection();
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			bufferedOutputStream = new BufferedOutputStream(urlConnection.getOutputStream());
			bufferedOutputStream.write(data.getBytes("UTF-8"));
			bufferedOutputStream.flush();
//			Set<Entry<String, List<String>>> set = urlConnection.getHeaderFields().entrySet();
			bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
			byte[] b = new byte[bufferedInputStream.available()];
			bufferedInputStream.read(b);
			bufferedOutputStream.close();
			bufferedInputStream.close();
			JSONObject jsonObject = JSONObject.fromObject(new String(b,"UTF-8"));
			responseEntity = new ResponseEntity();
			responseEntity.setCode(jsonObject.getJSONObject("status").get("errorCode").toString());
			responseEntity.setMsg(jsonObject.getJSONObject("status").get("errorMsg").toString());
			if (isJsonResult) {
//				responseEntity.setContent(JSONObject.fromObject(DES.DecodeCBC(jsonObject.get("bizObj").toString())));
				responseEntity.setContent(DES.DecodeCBC(jsonObject.get("bizObj").toString()));
			}else {
				responseEntity.setContent(jsonObject.get("bizObj").toString());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (bufferedOutputStream!=null) {
				try {
					bufferedOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bufferedInputStream!=null) {
				try {
					bufferedInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(responseEntity);
		return responseEntity;
	}
	

}
