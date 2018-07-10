package com.hxxc;


import java.util.HashMap;
import java.util.Map;

import com.hxxc.encrypt.DES;
import com.hxxc.entity.RequestEntity;

import net.sf.json.JSONObject;

public class testUtil {
	public static String createRequestParam(RequestEntity requestEntity){
		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap.put("command", requestEntity.getCommand());

		//模板head参数
		Map<String, Object> head = new HashMap<String, Object>();
		head.put("name", DES.EncodeCBC("胡黄涛"));
		head.put("phoneNum", DES.EncodeCBC("15350042700"));
//		head.put("pwd", DES.EncodeCBC("e10adc3949ba59abbe56e057f20f883e"));
		
//		head.put("businessType", "PublicPayMentHandlerImp");
		head.put("businessType", "BingoHandlerImp");

		requestMap.put("head", head);
		

		//加密某些数据
		String encryptParams = DES.EncodeCBC(JSONObject.fromObject(requestEntity.getParams()).toString());
		requestMap.put("params", encryptParams);

		String decodeCheck = DES.secretKey+encryptParams+DES.iv;
//		String encryptCheck = MD5.encrypt(decodeCheck);
//		requestMap.put("check", encryptCheck);

		String result = JSONObject.fromObject(requestMap).toString();
		System.out.println("请求参数："+result);
		return result;
	}

}
