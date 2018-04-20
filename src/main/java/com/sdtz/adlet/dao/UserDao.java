package com.sdtz.adlet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sdtz.adlet.entity.Ad;
import com.sdtz.adlet.entity.JobCode;
import com.sdtz.adlet.entity.User;
import com.sdtz.adlet.entity.UserLocation;


public interface UserDao {

	/**用户注册
	 * @param admin
	 * @return
	 */
	public int save (User admin);

	/**通过手机号查找用户
	 * @param phone
	 * @return
	 */
	public User findByPhone(String phone);
	
	/**登陆
	 * @param params
	 * @return
	 */
	public User login(HashMap<String, Object> params);
	
	/**通过用户id获取用户的详细信息
	 * @param id
	 * @return
	 */
	public User getUserInfo(int id);
	/**获取用户的余额
	 * @param id
	 * @return
	 */
	public User getBalance(int id);
	/**修改个人信息
	 * @param user
	 * @return
	 */
	public int update(User user);
	
	/**
	 * 修改个人信息不带头像
	 * @param user
	 * @return
	 */
	public int updatenoicon(User user);
	/**获取用户自己发布的广告
	 * @param params
	 * @return
	 */
	public List<Ad> getMyAds(Map<String,Object> params);
	/**删除某条广告
	 * @param id
	 * @return
	 */
	public int deleteAd(int id);
	
	/**保存用户的位置信息
	 * @param userLocation
	 * @return 返回一个int值如果大于零则表示保存成功
	 */
	public int saveUserLocation(UserLocation userLocation);
	
	/**
	 * 获取用户注册时需要选择的行业或职业
	 * @return
	 */
	public List<JobCode> getJobCode();
	
	/**
	 * 修该用户自己定义的广告顺序
	 * @param id
	 * @return
	 */
	public int updateMyType(User user); 
	
	/**
	 * 获取自己定义的广告顺序
	 * @param id
	 * @return
	 */
	public List<User> getMyType(int id);

	/**
	 * 取出所有用户
	 * @return
	 */
	public List<User>  findAllLocations();
	
	/**
	 * 获取当前位置的周边用户
	 * @return
	 */
	public List<UserLocation> findAllArountLocations();

	/**
	 * 置顶当前广告
	 * @param id
	 * @return
	 */
	public int pushAdTop(Integer id);

	/**
	 * 长连接注册用户之后，用户手机号注册（根据用户的username修改用户的信息）
	 * @param addUser
	 * @return
	 */
	public int saveUser(User addUser);

	/**
	 * 通过手机号获取用户详细信息
	 * @param phone
	 * @return
	 */
	public User getUserInfoByPhone(String phone);
	
	/**
	 * 删除之前老的登录记录
	 * @param username
	 * @return
	 */
	public int deleteOldLoginRecord(String username);

	/**
	 * 将老的用户信息保存到新的连接账号记录中
	 * @param user
	 */
	public void saveOldLoginRecord(User user);
}
