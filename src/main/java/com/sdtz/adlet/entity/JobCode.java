package com.sdtz.adlet.entity;

import java.io.Serializable;

public class JobCode implements Serializable{
	private int id;
	private String jobcode;
	private String jobname;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJobcode() {
		return jobcode;
	}
	public void setJobcode(String jobcode) {
		this.jobcode = jobcode;
	}
	public String getJobname() {
		return jobname;
	}
	public void setJobname(String jobname) {
		this.jobname = jobname;
	}
	
}
