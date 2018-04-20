package com.sdtz.adlet.util;

public class AdResult {
	private int status;// 0失败,1成功,2未知
	private String msg;//消息
	private Object data;//返回结果

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
