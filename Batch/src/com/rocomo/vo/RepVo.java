package com.rocomo.vo;

public class RepVo {
	private String id;
	private String coin;
	private String avg;
	private String ldavg;
	private String regDttm;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCoin() {
		return coin;
	}
	public void setCoin(String coin) {
		this.coin = coin;
	}
	public String getAvg() {
		return avg;
	}
	public void setAvg(String avg) {
		this.avg = avg;
	}
	public String getLdavg() {
		return ldavg;
	}
	public void setLdavg(String ldavg) {
		this.ldavg = ldavg;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	@Override
	public String toString() {
		return "RepVo [id=" + id + ", coin=" + coin + ", avg=" + avg + ", ldavg=" + ldavg + ", regDttm=" + regDttm
				+ "]";
	}
	
}
