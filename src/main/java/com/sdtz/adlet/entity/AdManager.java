package com.sdtz.adlet.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class AdManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;//本条记录的id
	private int ad_id;//本条广告的id
	private int user_id;//发布本条广告的者的id
	private String ad_title;//本条广告的标题
	private String areacode;//发布者的所属区域管理者号
	private Timestamp publish_time;//发布时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAd_id() {
		return ad_id;
	}
	public void setAd_id(int ad_id) {
		this.ad_id = ad_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getAd_title() {
		return ad_title;
	}
	public void setAd_title(String ad_title) {
		this.ad_title = ad_title;
	}
	public String getAreacode() {
		return areacode;
	}
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	public Timestamp getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(Timestamp publish_time) {
		this.publish_time = publish_time;
	}
	
	
}
