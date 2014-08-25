package com.example.userapply;

import java.io.Serializable;

public class BuyTypes implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String buytypes;
	String sumAmount;
	String balanceDt;
	
	
	
	public String getSumAmount() {
		return sumAmount;
	}
	public void setSumAmount(String sumAmount) {
		this.sumAmount = sumAmount;
	}
	public String getBalanceDt() {
		return balanceDt;
	}
	public void setBalanceDt(String balanceDt) {
		this.balanceDt = balanceDt;
	}
	public String getBuytypes() {
		return buytypes;
	}
	public void setBuytypes(String buytypes) {
		this.buytypes = buytypes;
	}
}
