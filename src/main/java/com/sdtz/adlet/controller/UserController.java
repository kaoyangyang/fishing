package com.sdtz.adlet.controller;

import com.sdtz.adlet.entity.User;
import com.sdtz.adlet.entity.UserLocation;
import com.sdtz.adlet.service.UserService;
import com.sdtz.adlet.util.AdResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
public class UserController {	

	@Resource
	private UserService userservice;
	//获取验证码接口
	@RequestMapping("/user/getcode.do")
	@ResponseBody
	public AdResult getCode(String phone){
		AdResult result=userservice.getCode(phone);	
		return result;
	}
	//注册接口
	@RequestMapping("/user/register.do")
	@ResponseBody
	public AdResult register(String username,String phone,String login_password,Integer code) throws Exception{
		AdResult result = userservice.register(username,phone,login_password,code);
		return result;
	}
	//登陆接口
	@RequestMapping("/user/login.do")
	@ResponseBody
	public AdResult login(String phone,String login_password,String username) throws Exception{
		AdResult result=userservice.login(phone,login_password,username);
		return result;	
	}
	//获取用户的详细信息
	@RequestMapping("/user/getUserInfo.do")
	@ResponseBody
	public AdResult getUserInfo(int id){
		//System.out.println(id);
		AdResult result=userservice.getUserInfo(id);
		return result;
	}
	//获取用户的详细信息
	@RequestMapping("/user/getUserInfoByPhone.do")
	@ResponseBody
	public AdResult getUserInfoByPhone(String phone){
		System.out.println(phone);
		AdResult result=userservice.getUserInfoByPhone(phone);
		return result;
	}
	//获取用户余额
	@RequestMapping("/user/getBalance.do")
	@ResponseBody
	public AdResult getBalance(int id){
		AdResult result = userservice.getBalance(id);
		return result;
		
	}
	//修改个人信息
	@RequestMapping("/user/update.do")
	@ResponseBody
	public AdResult update(User user,HttpServletRequest request,@RequestParam("file") CommonsMultipartFile file) throws Exception{
		AdResult result=userservice.update(user,request,file);
		return result;
	}
	//修改个人信息
	@RequestMapping("/user/updatenoicon.do")
	@ResponseBody
	public AdResult update(User user) throws Exception{
		System.out.println(user.getAreadesc());
		AdResult result=userservice.update(user);
		return result;
	}
	//获取用自己发布的广告
	@RequestMapping("/user/getMyAds.do")
	@ResponseBody
	public AdResult getMyAds(int user_id,int pageIndex,int pageSize){
	    AdResult result=userservice.getMyAds(user_id, pageIndex, pageSize);
		return result;
	}
	//删除广告
	@RequestMapping("/user/deleteAd.do")
	@ResponseBody
	public AdResult deleteAd(int id){
		AdResult result=userservice.deleteAd(id);
		return result;
	}
	//置顶广告
	@RequestMapping("/user/pushAdTop.do")
	@ResponseBody
	public AdResult pushAdTop(Integer id){
		AdResult result=userservice.pushAdTop(id);
		return result;
	}
	//保存用户位置信息
	@RequestMapping("/user/saveUserLocation.do")
	@ResponseBody
	public AdResult saveUserLocation(UserLocation userLocation){
		AdResult result=userservice.saveUserLocation(userLocation);
		return result;
	}
	//获取用户的职业行业选择
	@RequestMapping("/user/getJobCode.do")
	@ResponseBody
	public AdResult getJobCode(){
		AdResult result=userservice.getJobCode();
		return result;
	}
	
	//修改用户自己定义的广告的顺序
	@RequestMapping("/user/updateMyType.do")
	@ResponseBody
	public AdResult updateMyType(User user){
		AdResult adResult=userservice.updateMyType(user);
		return adResult;
	}
	
	@RequestMapping(value="/user/getMyType.do",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getMyType(int id){
		//System.out.println(id);
		String myTypeStr=userservice.getMyType(id);
		return myTypeStr;
		
	}
	//获取根据圈图选中的用户
	@RequestMapping("/user/getByCircle.do")
	@ResponseBody
	public AdResult getByCircle(double lon,double lat,int radius){
		System.out.println("123");
		AdResult adResult = userservice.findAll( lon, lat, radius);
		return adResult;
	}
	//根据用户当前位置获取周边用户
	@RequestMapping("/user/getUserLocations.do")
	@ResponseBody
	public AdResult getUserLocations(double lon,double lat,int radius){
		AdResult adResult = userservice.findAllAround( lon, lat, radius);
		return adResult;
	}
}
