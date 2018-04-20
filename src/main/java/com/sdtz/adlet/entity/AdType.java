package com.sdtz.adlet.entity;

import java.io.Serializable;

public class AdType implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int firsttype;
	private int secondtype;
	private String adname;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFirsttype() {
		return firsttype;
	}
	public void setFirsttype(int firsttype) {
		this.firsttype = firsttype;
	}
	public int getSecondtype() {
		return secondtype;
	}
	public void setSecondtype(int secondtype) {
		this.secondtype = secondtype;
	}
	public String getAdname() {
		return adname;
	}
	public void setAdname(String adname) {
		this.adname = adname;
	}
	
	
}
