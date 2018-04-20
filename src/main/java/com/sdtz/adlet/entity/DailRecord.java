package com.sdtz.adlet.entity;

import java.io.Serializable;
import java.util.Date;

public class DailRecord implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 本条打电话的id
	 */
	private Integer id;
	/**
	 * 那个用户打的这个电话
	 */
	private Integer userid;
	/**
	 * 被打的电话号码
	 */
	private String telnumber;
	/**
	 * 打此电话的时间
	 */
	private Date calltime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getTelnumber() {
		return telnumber;
	}
	public void setTelnumber(String telnumber) {
		this.telnumber = telnumber;
	}
	public Date getCalltime() {
		return calltime;
	}
	public void setCalltime(Date calltime) {
		this.calltime = calltime;
	}
	@Override
	public String toString() {
		return "DailRecord [id=" + id + ", userid=" + userid + ", telnumber=" + telnumber + ", calltime=" + calltime
				+ "]";
	}
	
	
}
