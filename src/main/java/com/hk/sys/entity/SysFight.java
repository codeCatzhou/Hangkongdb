package com.hk.sys.entity;

import java.io.Serializable;
import java.util.Date;

public class SysFight implements Serializable{
	
	private static final long serialVersionUID = 6327558478234191778L;
	private Integer id;//id
	private String fightId;//航班编号
	private String fromAdd;//出发地点
	private String toAdd;//目的地
	private Date startTime;//出发时间
	private Date arrTime;//到达时间
	private Integer status;//状态
	private Date creatTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFightId() {
		return fightId;
	}
	public void setFightId(String fightId) {
		this.fightId = fightId;
	}
	public String getFromAdd() {
		return fromAdd;
	}
	public void setFromAdd(String fromAdd) {
		this.fromAdd = fromAdd;
	}
	public String getToAdd() {
		return toAdd;
	}
	public void setToAdd(String toAdd) {
		this.toAdd = toAdd;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getArrTime() {
		return arrTime;
	}
	public void setArrTime(Date endTime) {
		this.arrTime = endTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	@Override
	public String toString() {
		return "SysFight [id=" + id + ", fightId=" + fightId + ", fromAdd=" + fromAdd + ", toAdd=" + toAdd
				+ ", startTime=" + startTime + ", arrTime=" + arrTime + ", status=" + status + ", creatTime="
				+ creatTime + "]";
	}
}
