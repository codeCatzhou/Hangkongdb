package com.hk.sys.entity;

import java.io.Serializable;
import java.util.Date;

public class SysOrder implements Serializable{
	
	private static final long serialVersionUID = 8103530059224811669L;
	private Integer id;
	private String username;
	private String name;
	private String identity;
	private String fightId;
	private String seatId;
	private Double cost;
	private Date creatTime;
	private Integer status;
	private Integer view;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getFightId() {
		return fightId;
	}
	public void setFightId(String fightId) {
		this.fightId = fightId;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getView() {
		return view;
	}
	public void setView(Integer view) {
		this.view = view;
	}
	@Override
	public String toString() {
		return "SysOrder [id=" + id + ", username=" + username + ", name=" + name + ", identity=" + identity
				+ ", fightId=" + fightId + ", seatId=" + seatId + ", cost=" + cost + ", creatTime=" + creatTime
				+ ", status=" + status + ", view=" + view + "]";
	}
	
}
