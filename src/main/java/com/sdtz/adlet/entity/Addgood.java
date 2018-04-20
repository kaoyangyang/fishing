package com.sdtz.adlet.entity;

import java.io.Serializable;

public class Addgood implements Serializable{

	public static final long serialVersionUID=1L;
	/**点赞的id*/
	private Integer id;
	/**点赞用户的id*/
	private Integer userid;
	/**点赞广告的id*/
	private Integer adid;
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
	public Integer getAdid() {
		return adid;
	}
	public void setAdid(Integer adid) {
		this.adid = adid;
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
		return "Addgood [id=" + id + ", userid=" + userid + ", adid=" + adid + ", commentid=" + commentid + ", icon="
				+ icon + "]";
	}
	
	
	
}
