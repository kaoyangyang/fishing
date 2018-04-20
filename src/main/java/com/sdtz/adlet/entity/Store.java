package com.sdtz.adlet.entity;

/**
 * Store entity. @author MyEclipse Persistence Tools
 */

public class Store implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 某个店的id采用用户本身的id
	 */
	private Integer storeid;
	/**
	 * 店的名字
	 */
	private String name;
	/**
	 * 店的地址
	 */
	private String address;
	/**
	 * 店的电话
	 */
	private String tel;
	
	private String lon;
	private String lat;
	/**
	 * 店的经营范围（关键字可以作为以后检索条件）
	 */
	private String keyword;
	/**
	 * 店的门面照
	 */
	private String icon;
	/**
	 * 所经营的事业的内容宣传照集合
	 */
	private String photos;
	
	/**
	 * 被删除的图片
	 */
	private String deletePhotos;

	// Constructors

	/** default constructor */
	public Store() {
	}


	

	public String getDeletePhotos() {
		return deletePhotos;
	}




	public void setDeletePhotos(String deletePhotos) {
		this.deletePhotos = deletePhotos;
	}




	public Integer getStoreid() {
		return storeid;
	}




	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}
	
	




	public String getLon() {
		return lon;
	}




	public void setLon(String lon) {
		this.lon = lon;
	}




	public String getLat() {
		return lat;
	}




	public void setLat(String lat) {
		this.lat = lat;
	}




	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPhotos() {
		return this.photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}
	
	


	@Override
	public String toString() {
		return "Store [user_id=" + storeid + ", name=" + name + ", address=" + address + ", tel=" + tel + ", keyword="
				+ keyword + ", icon=" + icon + ", photos=" + photos + "]";
	}

}