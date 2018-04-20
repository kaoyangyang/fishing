package com.sdtz.adlet.entity;

import java.util.Date;

import javax.persistence.Column;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	/**
	 * 用户属性
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//用户id
	private String username;//unique
	private String email;//邮箱
	private String name;//名字
	private String phone;//电话号码
	private String login_password;//登录密码
	private String password;//认证密码
	private String nickname;//昵称
	private Date createdDate;//创建时间
	private Date updatedDate;//修改时间
	private Integer age;//年龄
	private String sex;//性别
	private String job_id;//职业id
	private Double balance;//钱包余额
	private Double bean;//积分豆
	private String icon;//头像
	private String province;//省
	private String city;//市
	private String district;//区
	private String town;//镇
	private Integer admin_id;//管理员
	private Integer status;//状态
	private String alipay;//支付宝账号
	private String wepay;//微信 账号
	private Integer total_recharge;//总的充值
	private Integer total_cash;//总的提现
	private String job_name;//职业名称
	private String mydescription;//个性签名
	private String areadesc;//地址名称
	private String myType;//自定义的广告类型排序
	private Double lng;//经度
	private Double lat;//纬度
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getLogin_password() {
		return login_password;
	}
	public void setLogin_password(String login_password) {
		this.login_password = login_password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Double getBean() {
		return bean;
	}
	public void setBean(Double bean) {
		this.bean = bean;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
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
	
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAlipay() {
		return alipay;
	}
	public void setAlipay(String alipay) {
		this.alipay = alipay;
	}
	public String getWepay() {
		return wepay;
	}
	public void setWepay(String wepay) {
		this.wepay = wepay;
	}
	public Integer getTotal_recharge() {
		return total_recharge;
	}
	public void setTotal_recharge(Integer total_recharge) {
		this.total_recharge = total_recharge;
	}
	public Integer getTotal_cash() {
		return total_cash;
	}
	public void setTotal_cash(Integer total_cash) {
		this.total_cash = total_cash;
	}

	
	public String getJob_name() {
		return job_name;
	}
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}
	
	
	public String getMydescription() {
		return mydescription;
	}
	public void setMydescription(String mydescription) {
		this.mydescription = mydescription;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getTwon() {
		return town;
	}
	public void setTwon(String town) {
		this.town = town;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	
	public String getAreadesc() {
		return areadesc;
	}
	public void setAreadesc(String areadesc) {
		this.areadesc = areadesc;
	}
	public String getMyType() {
		return myType;
	}
	public void setMyType(String myType) {
		this.myType = myType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", name=" + name + ", phone=" + phone
				+ ", login_password=" + login_password + ", password=" + password + ", nickname=" + nickname
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", age=" + age + ", sex=" + sex
				+ ", job_id=" + job_id + ", balance=" + balance + ", bean=" + bean + ", icon=" + icon + ", province="
				+ province + ", city=" + city + ", district=" + district + ", town=" + town + ", admin_id=" + admin_id
				+ ", status=" + status + ", alipay=" + alipay + ", wepay=" + wepay + ", total_recharge="
				+ total_recharge + ", total_cash=" + total_cash + ", job_name=" + job_name + ", mydescription="
				+ mydescription + ", areadesc=" + areadesc + ", myType=" + myType + ", lng=" + lng + ", lat=" + lat
				+ "]";
	}
	
	
	
	
	
	
	

}