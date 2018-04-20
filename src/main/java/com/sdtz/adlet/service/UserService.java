
package com.sdtz.adlet.service;

import com.sdtz.adlet.entity.User;
import com.sdtz.adlet.entity.UserLocation;
import com.sdtz.adlet.util.AdResult;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


public interface UserService {
	

	public User login(HashMap<String, Object> params);
	
	/** 用户注册接口,用户注册用户
	 * @param phone
	 * @param password
	 * @param code
	 * @return 
	 * @throws Exception 
	 */
	public AdResult register(String username,String phone, String login_password, Integer code) throws Exception;

	/**用户注册时获取验证码接口
	 * @param phone
	 * @return
	 */
	public AdResult getCode(String phone);

	/**用户登录接口
	 * @param phone
	 * @param password
	 * @return
	 */
	public AdResult login(String phone, String login_password,String username);
	
	/**获取用户详细信息
	 * @param id
	 * @return
	 */
	public AdResult getUserInfo(int id);
	/**获取用户余额
	 * @param id
	 * @return
	 */
	public AdResult getBalance(int id);
	/**修改个人信息不带头像的方法
	 * @param user
	 * @return
	 */
	public AdResult update(User user); 
	
	/**修改个人信息时带有头像的情况
	 * @param user
	 * @param file
	 * @return
	 */
	public AdResult update(User user,HttpServletRequest request,CommonsMultipartFile file);
	/**获取用户自己发布的广告
	 * @param user_id
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public AdResult getMyAds(int user_id,int pageIndex,int pageSize);
	
	/**删除广告
	 * @param id
	 * @return
	 */
	public AdResult deleteAd(int id);
	
	/**保存用户位置信息
	 * @param userLocation
	 * @return
	 */
	public AdResult saveUserLocation(UserLocation userLocation);
	
	/**
	 * 获取行业职业
	 * @return
	 */
	public AdResult getJobCode();
	
	
	/**
	 * 修改我的类型
	 * @return
	 */
	public AdResult updateMyType(User user);
	
	
	public String getMyType(int id);

	/**
	 * 获取所有用户
	 * @return
	 */
	public AdResult findAll(double lon,double lat,int radius);
	
	/**
	 * 根据当前用户的位置获取周边用户
	 * @param lon
	 * @param lat
	 * @param radius
	 * @return
	 */
	public AdResult findAllAround(double lon,double lat,int radius);

	/**
	 * 置顶当前广告
	 * @param id
	 * @return
	 */
	public AdResult pushAdTop(Integer id);

	/**
	 * 通过手机号获取用户详细信息
	 * @param phone
	 * @return
	 */
	public AdResult getUserInfoByPhone(String phone);
}
 