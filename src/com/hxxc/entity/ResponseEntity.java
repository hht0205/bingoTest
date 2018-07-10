package com.hxxc.entity;

import net.sf.json.JSONObject;

public class ResponseEntity {
	private String code;
	private String msg;
	private Object content;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	public String toString(){
		return code+" , \n"+msg+" , \n"+content.toString();
	}

}
