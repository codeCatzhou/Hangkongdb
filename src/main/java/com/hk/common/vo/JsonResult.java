package com.hk.common.vo;

import java.io.Serializable;

public class JsonResult implements Serializable{
	
	private static final long serialVersionUID = -7304305715658377887L;
	/* 状态码：1表示ok，0表示error*/
	private int state = 1;
	/** 状态信息：状态码对应的具体信息*/
	private String message="ok";
	/** 正确数据（一般用于存储业务层返回的数据）*/
	private Object data;
	
	public JsonResult() {}
	public JsonResult(String message) {
		this.message = message;
	}

	public JsonResult(Object data) {
		this.data = data;
	}
	public JsonResult(Throwable t) {
		super();
		this.state = 0;
		this.message =t.getMessage();
	}

	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
