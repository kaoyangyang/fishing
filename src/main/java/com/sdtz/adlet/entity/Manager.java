package com.sdtz.adlet.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Manager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;//管理员id
	private String acount;//管理员账号
	private String password;//密码
	private char role;//角色 0,表示普通账号，1表示超级管理员账号
	private Timestamp  addtime;//创建时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAcount() {
		return acount;
	}
	public void setAcount(String acount) {
		this.acount = acount;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public char getRole() {
		return role;
	}
	public void setRole(char role) {
		this.role = role;
	}
	public Timestamp getAddtime() {
		return addtime;
	}
	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}
	@Override
	public String toString() {
		return "Manager [id=" + id + ", acount=" + acount + ", password=" + password + ", role=" + role + ", addtime="
				+ addtime + "]";
	}
	
}
