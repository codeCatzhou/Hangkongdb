package com.hk.view.vo;

import java.util.Date;

import com.hk.sys.entity.SysSeat;

public class ViewFightSeat {
	private Integer id;//id
	private String fightId;//航班编号
	private String fromAdd;//出发地点
	private String toAdd;//目的地
	private Date startTime;//出发时间
	private Date arrTime;//到达时间
	private SysSeat sysSeat;
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
	public void setArrTime(Date arrTime) {
		this.arrTime = arrTime;
	}
	public SysSeat getSysSeat() {
		return sysSeat;
	}
	public void setSysSeat(SysSeat sysSeat) {
		this.sysSeat = sysSeat;
	}
	@Override
	public String toString() {
		return "ViewFightSeat [id=" + id + ", fightId=" + fightId + ", fromAdd=" + fromAdd + ", toAdd=" + toAdd
				+ ", startTime=" + startTime + ", arrTime=" + arrTime + ", sysSeat=" + sysSeat + "]";
	}
}
