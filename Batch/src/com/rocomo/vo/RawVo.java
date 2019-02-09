package com.rocomo.vo;

public class RawVo {
	private double vol;
	private double amt;
	private double close;
	private String pair;
	private String coin;
	private String market;
	private String exchange;
	private String regDttm;
	
	public double getVol() {
		return vol;
	}
	public void setVol(double vol) {
		this.vol = vol;
	}
	public double getAmt() {
		return amt;
	}
	public void setAmt(double amt) {
		this.amt = amt;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public String getPair() {
		return pair;
	}
	public String getCoin() {
		return coin;
	}
	public void setCoin(String coin) {
		this.coin = coin;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public void setPair(String pair) {
		this.pair = pair;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	@Override
	public String toString() {
		return "RawVo [vol=" + vol + ", amt=" + amt + ", close=" + close + ", pair=" + pair + ", coin=" + coin
				+ ", market=" + market + ", exchange=" + exchange + ", regDttm=" + regDttm + "]";
	}
	
}
