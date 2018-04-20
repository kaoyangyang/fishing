package com.sdtz.adlet.controller;

import com.sdtz.adlet.entity.Ad;
import com.sdtz.adlet.entity.Manager;
import com.sdtz.adlet.service.AdService;
import com.sdtz.adlet.service.ManagerService;
import com.sdtz.adlet.service.ManagerServiceImpl;
import com.sdtz.adlet.util.AdResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 专门为管理员设置的接口控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {
	public static final String SERVER_URL="http://47.94.166.156:8080/adlet/";
	//public static final String LOCAL_SERVER_URL="http://127.0.0.1:8080/adlet/";
	@Resource
	private ManagerService managerService;
	@Resource
	private HttpServletRequest request;
	@Resource
	private AdService adService;

	
	//获取需要管理员审核的广告
	@RequestMapping("/getMyAreaNeedCheckAd.do")
	@ResponseBody
	public AdResult excute(){
		HttpSession session = request.getSession();
		Manager user = (Manager)session.getAttribute("user");
		AdResult adResult = new AdResult();
		if(user!=null){
			if(user.getRole()== ManagerServiceImpl.ADMIN_ROLE){
				adResult=managerService.getNeedCheckAd();
			}else{
				adResult=managerService.getMyAreaNeedCheckAd(user.getAcount());
			}

			adResult.setStatus(1);
			return adResult;
		}else{
			adResult.setStatus(0);
			return adResult;
		}

	}
	
//	//超级管理员账号获取所有需要审核的广告
//	@RequestMapping("/getNeedCheckAd.do")
//	@ResponseBody
//	public AdResult getNeedCheckAd(){
//		HttpSession session = request.getSession();
//		Manager user = (Manager)session.getAttribute("user");
//		if(user!=null){
//
//		}
//		AdResult adResult=managerService.getNeedCheckAd();
//		return adResult;
//	}
	
	//广告通过审核
	@RequestMapping("/passAd.do")
	@ResponseBody
	public AdResult passAd(Integer adid,Integer ispush){
		AdResult adResult=managerService.adIsOk(adid,ispush);
		return adResult;
	}
	
	//广告未通过审核
	@RequestMapping("/refuseAd.do")
	@ResponseBody
	public AdResult refuseAd(Integer adid){
		AdResult adResult=managerService.adIsBad(adid);
		return adResult;
	}
	//广告未通过审核
	@RequestMapping("/tocheck.do")
	public String tocheck(ModelMap map,Integer id,Integer ispush){
		System.out.println("ispush="+ispush+"id="+id);
		AdResult result=adService.getAdInfo(id);
		System.out.println(result.getData());
		map.addAttribute("info",result);
		map.addAttribute("id",id);
		map.addAttribute("ispush",ispush);
		map.addAttribute("serverUrl",SERVER_URL);
		//map.addAttribute("serverUrl",LOCAL_SERVER_URL);
		if(result.getStatus()==1){
			Ad ad = (Ad)result.getData();
			String photos = ad.getPhotos();
			if(photos!=null){
				String[] photosString = photos.split(",");
				List<String> photesList = new ArrayList<String>();
				for (String phote:photosString){
					photesList.add(phote);
				}
				map.addAttribute("photes",photesList);
			}
		}
		return "index/ad_detail";
	}
}
