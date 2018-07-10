package com.hxxc.entity;

import java.util.Map;

public class RequestEntity {
	
	private String command;
	private Map<String, Object> head;
	private Map<String, Object> params;
	private String check;
	
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public Map<String, Object> getHead() {
		return head;
	}
	public void setHead(Map<String, Object> head) {
		this.head = head;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	
	public RequestEntity(Map<String, Object> params,String command){
		this.params = params;
		this.command = command;
	}

}
