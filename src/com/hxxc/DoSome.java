package com.hxxc;


import java.util.HashMap;
import java.util.Map;

import com.hxxc.entity.RequestEntity;
import com.hxxc.entity.ResponseEntity;


public class DoSome {
	public static void getPlay(){
		Map<String, Object> params = new HashMap<String, Object>();
		RequestEntity requestEntity = new RequestEntity(params,"getPlay");
		ResponseEntity responseEntity = DoMethod.doSome(testUtil.createRequestParam(requestEntity),false);
	}
	
	public static void main(String[] args) {
		 getPlay();
	}
}
