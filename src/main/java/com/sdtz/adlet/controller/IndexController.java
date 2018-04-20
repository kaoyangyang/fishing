package com.sdtz.adlet.controller;

import com.sdtz.adlet.entity.Manager;
import com.sdtz.adlet.service.ManagerService;
import com.sdtz.adlet.util.AdResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
	@Resource
	private ManagerService managerService;
	@Resource
	private HttpServletRequest request;
	@RequestMapping("/index.do")
	//@ResponseBody
	public String index(ModelMap map){
		HttpSession session = request.getSession();
		//String acount=req.getParameter("acount");
		//获取session中作为判断的字段
		Manager user = (Manager)session.getAttribute("user");
		if(user!=null){
			return "index/index";
		}else{
			return "index/login";
		}

	}

	//登录
	@RequestMapping("/login.do")
	@ResponseBody
	public AdResult login(Manager manager){

		AdResult adResult=managerService.getManagerInfo(manager);
		if(adResult.getStatus()==1){
			//创建session对象
			HttpSession session = request.getSession();
			//把用户数据保存在session域对象中
			Manager user = (Manager)adResult.getData();
			session.setAttribute("user", user);
		}

		return adResult;
	}
	//登录页面
	@RequestMapping("/tologin.do")
	public String login(){

		return "index/login";
	}
}
