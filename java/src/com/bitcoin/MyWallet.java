package com.bitcoin;

public class MyWallet {
	String address = "";
	String balance = "";
	String seed = "";
	String creationTime = "";
	String mnemonics = "";

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Address : \t" + address);
		sb.append("\r\n");
		sb.append("Balance : \t" + balance);
		sb.append("\r\n");
		sb.append("Seed : \t" + seed);
		sb.append("\r\n");
		sb.append("Creationtime : \t" + creationTime);
		sb.append("\r\n");
		sb.append("Mnemonics : \t" + mnemonics);
		sb.append("\r\n");
		return sb.toString();
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getSeed() {
		return seed;
	}

	public void setSeed(String seed) {
		this.seed = seed;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String getMnemonics() {
		return mnemonics;
	}

	public void setMnemonics(String mnemonics) {
		this.mnemonics = mnemonics;
	}
	
}
