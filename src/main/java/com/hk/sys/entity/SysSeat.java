package com.hk.sys.entity;

import java.io.Serializable;

public class SysSeat implements Serializable{
	private static final long serialVersionUID = -3855665608393032029L;
	private Integer id;
	private String fightId;
	private Integer fnumbers;
	private Double fprice;
	private Integer snumbers;
	private Double sprice;
	private Integer onumbers;
	private Double oprice;
	private Double rebate;
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
	public Integer getFnumbers() {
		return fnumbers;
	}
	public void setFnumbers(Integer fnumbers) {
		this.fnumbers = fnumbers;
	}
	public Double getFprice() {
		return fprice;
	}
	public void setFprice(Double fprice) {
		this.fprice = fprice;
	}
	public Integer getSnumbers() {
		return snumbers;
	}
	public void setSnumbers(Integer snumbers) {
		this.snumbers = snumbers;
	}
	public Double getSprice() {
		return sprice;
	}
	public void setSprice(Double sprice) {
		this.sprice = sprice;
	}
	public Integer getOnumbers() {
		return onumbers;
	}
	public void setOnumbers(Integer onumbers) {
		this.onumbers = onumbers;
	}
	public Double getOprice() {
		return oprice;
	}
	public void setOprice(Double oprice) {
		this.oprice = oprice;
	}
	public Double getRebate() {
		return rebate;
	}
	public void setRebate(Double rebate) {
		this.rebate = rebate;
	}
	@Override
	public String toString() {
		return "SysSeat [id=" + id + ", fightId=" + fightId + ", fnumbers=" + fnumbers + ", fprice=" + fprice
				+ ", snumbers=" + snumbers + ", sprice=" + sprice + ", onumbers=" + onumbers + ", oprice=" + oprice
				+ ", rebate=" + rebate + "]";
	}
	
}
