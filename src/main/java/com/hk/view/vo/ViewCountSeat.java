package com.hk.view.vo;

public class ViewCountSeat {
	private String fightId;
	private Integer countf;
	private Integer counts;
	private Integer counto;
	public String getFightId() {
		return fightId;
	}
	public void setFightId(String fightId) {
		this.fightId = fightId;
	}
	public Integer getCountf() {
		return countf;
	}
	public void setCountf(Integer countf) {
		this.countf = countf;
	}
	public Integer getCounts() {
		return counts;
	}
	public void setCounts(Integer counts) {
		this.counts = counts;
	}
	public Integer getCounto() {
		return counto;
	}
	public void setCounto(Integer counto) {
		this.counto = counto;
	}
	@Override
	public String toString() {
		return "ViewCountSeat [fightId=" + fightId + ", countf=" + countf + ", counts=" + counts + ", counto=" + counto
				+ "]";
	}
	
}
