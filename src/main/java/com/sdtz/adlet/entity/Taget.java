package com.sdtz.adlet.entity;

/**
 * Taget entity. @author MyEclipse Persistence Tools
 */

public class Taget implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Integer id;
	private Integer job_id;
	private Integer ad_id;
	private Integer start_age;
	private Integer end_age;
	private String province;
	private String city;
	private String county;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getJob_id() {
		return job_id;
	}
	public void setJob_id(Integer job_id) {
		this.job_id = job_id;
	}
	public Integer getAd_id() {
		return ad_id;
	}
	public void setAd_id(Integer ad_id) {
		this.ad_id = ad_id;
	}
	public Integer getStart_age() {
		return start_age;
	}
	public void setStart_age(Integer start_age) {
		this.start_age = start_age;
	}
	public Integer getEnd_age() {
		return end_age;
	}
	public void setEnd_age(Integer end_age) {
		this.end_age = end_age;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}

}