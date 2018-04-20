package com.sdtz.adlet.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Ad entity. @author MyEclipse Persistence Tools
 */

public class Ad implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;//广告id
	private String ad_id;//广告唯一标示
	private Integer cat_id;//浏览者id
	private Integer user_id;//发广告的用户id
	private String title;//广告的标题
	private String content;//广告的内容
	private String photos;//广告的图片存在数据库的相对路径地址
	private String address;//用户输入的地址
	private Double lng;//经度
	private Double lat;//维度
	private String province;//省
	private String city;//市
	private String district;//县、区
	private String town;//乡镇
	private Double price;//单条广告的价格
	private Integer status;//状态0表示其他用户不可见,1表示可见,2标示等待审核
	private Integer ischecked;//是否审核过了，0表示未审核，1表示已审核
	private Integer ispush;//是否时推送广告,0表示不是，1表示是
    private Timestamp pull_time;//发布时间
	//private Date pull_time;
	private Date modify_time;//修改时间
	private Integer number;//推送广告的数量
	private Double total;//总花费
	private Double radius;//推送半径
	private Integer type;//类型
	private Integer clicks;//点击量
	private String tel;//电话
	private Integer secondtype;//次类型
	private Integer secondtype1;//次类型2
	private Integer secondtype2;//次类型3
	private Integer secondtype3;//次类型4
	private Integer secondtype4;//次类型5
	private String nickname; //发布者昵称
	private User user;//发布者
	private List<Comment> comments;//该条广告的评论
	private String icon;//头像
	private String country;//国家编码
	private String belongArea;//所属管理区域
	private String publishAreaName;//发布区域的名字
	
	private Integer agestart;//推送对象的最小年龄
	private Integer ageend; //推送对象的最大年龄
	private String sex;//推送对象的性别0表示女，1表示男
	private String job_id;//推送对象的职业编码
	private String pushmethod;//推送方式0表示区域推送，1表示定位推送
	

	
	
	
	public Integer getAgestart() {
		return agestart;
	}
	public void setAgestart(Integer agestart) {
		this.agestart = agestart;
	}
	public Integer getAgeend() {
		return ageend;
	}
	public void setAgeend(Integer ageend) {
		this.ageend = ageend;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPushmethod() {
		return pushmethod;
	}
	public void setPushmethod(String pushmethod) {
		this.pushmethod = pushmethod;
	}
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	public String getPublishAreaName() {
		return publishAreaName;
	}
	public void setPublishAreaName(String publishAreaName) {
		this.publishAreaName = publishAreaName;
	}
	public Integer getIspush() {
		return ispush;
	}
	public void setIspush(Integer ispush) {
		this.ispush = ispush;
	}
	public Integer getIschecked() {
		return ischecked;
	}
	public void setIschecked(Integer ischecked) {
		this.ischecked = ischecked;
	}
	public String getBelongArea() {
		return belongArea;
	}
	public void setBelongArea(String belongArea) {
		this.belongArea = belongArea;
	}
	public String getAd_id() {
		return ad_id;
	}
	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public Timestamp getPull_time() {
		return pull_time;
	}
	public void setPull_time(Timestamp pull_time) {
		this.pull_time = pull_time;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCat_id() {
		return cat_id;
	}
	public void setCat_id(Integer cat_id) {
		this.cat_id = cat_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	public String getPhotos() {
		return photos;
	}
	public void setPhotos(String photos) {
		this.photos = photos;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
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
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getRadius() {
		return radius;
	}
	public void setRadius(Double radius) {
		this.radius = radius;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getClicks() {
		return clicks;
	}
	public void setClicks(Integer clicks) {
		this.clicks = clicks;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getSecondtype() {
		return secondtype;
	}
	public void setSecondtype(Integer secondtype) {
		this.secondtype = secondtype;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getSecondtype1() {
		return secondtype1;
	}
	public void setSecondtype1(Integer secondtype1) {
		this.secondtype1 = secondtype1;
	}
	public Integer getSecondtype2() {
		return secondtype2;
	}
	public void setSecondtype2(Integer secondtype2) {
		this.secondtype2 = secondtype2;
	}
	public Integer getSecondtype3() {
		return secondtype3;
	}
	public void setSecondtype3(Integer secondtype3) {
		this.secondtype3 = secondtype3;
	}
	public Integer getSecondtype4() {
		return secondtype4;
	}
	public void setSecondtype4(Integer secondtype4) {
		this.secondtype4 = secondtype4;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "Ad [id=" + id + ", ad_id=" + ad_id + ", cat_id=" + cat_id + ", user_id=" + user_id + ", title=" + title
				+ ", content=" + content + ", photos=" + photos + ", address=" + address + ", lng=" + lng + ", lat="
				+ lat + ", province=" + province + ", city=" + city + ", district=" + district + ", town=" + town
				+ ", price=" + price + ", status=" + status + ", ischecked=" + ischecked + ", ispush=" + ispush
				+ ", pull_time=" + pull_time + ", modify_time=" + modify_time + ", number=" + number + ", total="
				+ total + ", radius=" + radius + ", type=" + type + ", clicks=" + clicks + ", tel=" + tel
				+ ", secondtype=" + secondtype + ", secondtype1=" + secondtype1 + ", secondtype2=" + secondtype2
				+ ", secondtype3=" + secondtype3 + ", secondtype4=" + secondtype4 + ", nickname=" + nickname + ", user="
				+ user + ", comments=" + comments + ", icon=" + icon + ", country=" + country + ", belongArea="
				+ belongArea + ", publishAreaName=" + publishAreaName + ", agestart=" + agestart + ", ageend=" + ageend
				+ ", sex=" + sex + ", job_id=" + job_id + ", pushmethod=" + pushmethod + "]";
	}
	
	
	
	
}