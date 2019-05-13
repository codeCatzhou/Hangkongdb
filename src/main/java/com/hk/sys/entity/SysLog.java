package com.hk.sys.entity;

import java.io.Serializable;
import java.util.Date;

public class SysLog implements Serializable{
	private static final long serialVersionUID = 8040171936713748825L;
	private Integer id;
	private String username;
	private String operation;
	private Date createdTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	@Override
	public String toString() {
		return "SysLog [id=" + id + ", username=" + username + ", operation=" + operation + ", createdTime="
				+ createdTime + "]";
	}
	
	
	
}
