package com.sdtz.adlet.entity;

import java.io.Serializable;
import java.util.Date;

public class StoreCommentReply implements Serializable{

	/**版本号*/
	private static final long serialVersionUID = 1L;
	/**某条事业回复的评论id*/
	private Integer id;
	/**评论回复的内容*/
	private String content;
	/**评论回复者的id号*/
	private Integer userid;
	/**评论回复者的头像*/
	private String icon;
	/**评论回复者的昵称(默认值：路人甲)*/
	private String nickname;
	/**当前评论回复的回复数量*/
	private Integer commentnumber;
	/**当前回复对应的事业留言的id*/
	private Integer commentid;
	/**对当前的评论点赞的数量*/
	private Integer goodnumber;
	/**发布时间*/
	private Date addtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
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
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	@Override
	public String toString() {
		return "StoreCommentReply [id=" + id + ", content=" + content + ", userid=" + userid + ", icon=" + icon
				+ ", nickname=" + nickname + ", commentnumber=" + commentnumber + ", commentid=" + commentid
				+ ", goodnumber=" + goodnumber + ", addtime=" + addtime + "]";
	}
	
	
}
