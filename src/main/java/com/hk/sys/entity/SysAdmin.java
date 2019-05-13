package com.hk.sys.entity;

import java.io.Serializable;
import java.util.Date;

public class SysAdmin implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String password;
	private String salt;
	private Integer vaild;
	private Date creatTime;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public Integer getVaild() {
		return vaild;
	}
	public void setVaild(Integer vaild) {
		this.vaild = vaild;
	}
	
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	@Override
	public String toString() {
		return "SysAdmin [id=" + id + ", username=" + username + ", password=" + password + ", salt=" + salt
				+ ", vaild=" + vaild + ", creatTime=" + creatTime + "]";
	}
	
}
