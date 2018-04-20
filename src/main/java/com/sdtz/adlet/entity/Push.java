package com.sdtz.adlet.entity;

import java.util.Date;

/**
 * Push entity. @author MyEclipse Persistence Tools
 */

public class Push implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Integer id;
	private Integer user_id;
	private Integer ad_id;
	private Date time;
	private Integer accept;
	private Double price;
	private Date time_limit;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getAd_id() {
		return ad_id;
	}
	public void setAd_id(Integer ad_id) {
		this.ad_id = ad_id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getAccept() {
		return accept;
	}
	public void setAccept(Integer accept) {
		this.accept = accept;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getTime_limit() {
		return time_limit;
	}
	public void setTime_limit(Date time_limit) {
		this.time_limit = time_limit;
	}


}