package com.sdtz.adlet.entity;

import java.io.Serializable;
import java.util.Date;


public class Comment implements Serializable{

	private static final long serialVersionUID = 1L;
	/**评论的id*/
	private Integer id;
	/**广告的id*/
	private Integer adid;
	/**用户的id*/
	private Integer userid;
	/**评论内容*/
	private String content;
	/**评论的时间*/
	private Date addtime;
	/**评论者的头像地址*/
	private String icon;
	/**评论者的昵称*/
	private String nickname;
	/**当前评论的追评次数*/
	private Integer commentnumber;
	/**对当前评论的id*/
	private Integer commentid;
	/**点赞数*/
	private Integer goodnumber;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAdid() {
		return adid;
	}
	public void setAdid(Integer adid) {
		this.adid = adid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getCommentnumber() {
		return commentnumber;
	}
	public void setCommentnumber(Integer commentnumber) {
		this.commentnumber = commentnumber;
	}
	
	public Integer getCommentid() {
		return commentid;
	}
	public void setCommentid(Integer commentid) {
		this.commentid = commentid;
	}
	
	public Integer getGoodnumber() {
		return goodnumber;
	}
	public void setGoodnumber(Integer goodnumber) {
		this.goodnumber = goodnumber;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", adid=" + adid + ", userid=" + userid + ", content=" + content + ", addtime="
				+ addtime + ", icon=" + icon + ", nickname=" + nickname + ", commentnumber=" + commentnumber
				+ ", commentid=" + commentid + ", goodnumber=" + goodnumber + "]";
	}
	
	
	
	
}
