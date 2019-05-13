package com.hk.view.vo;

import java.io.Serializable;

public class ViewOrderOut implements Serializable{
	
	private static final long serialVersionUID = -6977617864655010923L;
	private Integer id;
	private String fightId;
	private String seatId;
	
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
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	@Override
	public String toString() {
		return "ViewOrderOut [id=" + id + ", fightId=" + fightId + ", seatId=" + seatId + "]";
	}
	
}
