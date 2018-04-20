package com.sdtz.adlet.entity;

import java.io.Serializable;

public class Storegood implements Serializable{

	public static final long serialVersionUID=1L;
	/**点赞的id*/
	private Integer id;
	/**点赞用户的id*/
	private Integer userid;
	/**点赞广告的id*/
	private Integer storeid;
	/**点赞评论的id*/
	private Integer commentid;
	/**点赞用户头像*/
	private String icon;
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
	public Integer getStoreid() {
		return storeid;
	}
	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}
	public Integer getCommentid() {
		return commentid;
	}
	public void setCommentid(Integer commentid) {
		this.commentid = commentid;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	@Override
	public String toString() {
		return "Storegood [id=" + id + ", userid=" + userid + ", storeid=" + storeid + ", commentid=" + commentid
				+ ", icon=" + icon + "]";
	}
	
	
}
