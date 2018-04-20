package com.sdtz.adlet.service;

import com.sdtz.adlet.controller.PushController;
import com.sdtz.adlet.dao.AdDao;
import com.sdtz.adlet.dao.ManagerDao;
import com.sdtz.adlet.entity.Ad;
import com.sdtz.adlet.entity.Manager;
import com.sdtz.adlet.util.AdResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagerServiceImpl implements ManagerService{
	//超级管理员
	public static final char ADMIN_ROLE='1';
	//普通管理员
	public static final char NORMAL_ROLE='0';
	@Resource
	private ManagerDao managerDao;
	@Resource
	private AdDao adDao;
	
	@Override
	public AdResult getMyAreaNeedCheckAd(String acount) {
		AdResult adResult=new AdResult();
		List<Ad> ads=managerDao.getMyAreaNeedCheckAd(acount);
		if(ads.size()>0){
			adResult.setData(ads);
			adResult.setMsg("获取成功");
			adResult.setStatus(1);
		}else{
			adResult.setMsg("未获取到数据");
			adResult.setStatus(0);
		}
		return adResult;
	}

	@Override
	public AdResult getNeedCheckAd() {
		AdResult adResult=new AdResult();
		List<Ad> ads=managerDao.getNeedCheckAd();
		if(ads.size()>0){
			System.out.println("dssd");
			adResult.setData(ads);
			adResult.setMsg("获取成功");
			adResult.setStatus(1);
		}else{
			adResult.setMsg("未获取到数据");
			adResult.setStatus(0);
		}
		return adResult;
	}

	@Override
	public AdResult getManagerInfo(Manager manager) {
		AdResult adResult=new AdResult();
		Manager managerresult = managerDao.getManagerInfoByAcount(manager);
		if(managerresult!=null){
			if(managerresult.getPassword().equals(manager.getPassword())){
				adResult.setData(managerresult);
				adResult.setMsg("获取到了管理者");
				adResult.setStatus(1);
			}else{
				adResult.setMsg("密码错误");
				adResult.setStatus(2);
			}
		}else{
			adResult.setMsg("账号不存在");
			adResult.setStatus(0);
		}
		return adResult;
	}

	@Override
	public AdResult adIsOk(Integer adid,Integer ispush) {
		AdResult adResult=new AdResult();
		int result=managerDao.updateAdStatusOk(adid);
		System.out.println("修改结果："+result);
		if(result>0){
			adResult.setMsg("审核通过");
			adResult.setStatus(1);
			if(ispush==1){
				//获取当前推送广告的详情
				Ad ad=adDao.getAdById(adid);
				System.out.println("ad:"+ad);
				//是推送广告调用推送接口
				HashMap<String, String> map=new HashMap<String, String>();
				map.put("id", String.valueOf(ad.getId()));
				if(ad.getTitle()!=null){
					map.put("title", ad.getTitle());
				}
				if(ad.getContent()!=null){
					map.put("content", ad.getContent());
				}
				if(ad.getAgestart()!=null){
					map.put("agestart", String.valueOf(ad.getAgestart()));
				}
		    	if(ad.getAgeend()!=null){
		    		map.put("ageend",String.valueOf(ad.getAgeend()));
		    	}
		    	if(ad.getSex()!=null){
		    		map.put("sex", ad.getSex());
		    	}
		    	if(ad.getJob_id()!=null){
		    		map.put("job_id", ad.getJob_id());
		    	}
		    	if(ad.getLng()!=null){
		    		map.put("lng", String.valueOf(ad.getLng()));
		    	}
		    	if(ad.getLat()!=null){
		    		map.put("lat", String.valueOf(ad.getLat()));
		    	}
		    	if(ad.getRadius()!=null){
		    		map.put("radius", String.valueOf(ad.getRadius()));
		    	}
		    	if(ad.getPushmethod()!=null){
		    		map.put("pushmethod", ad.getPushmethod());
		    	}
		    	if(ad.getTown()!=null && !ad.getTown().equals("")){
		    		map.put("areaname", "town");
			    	map.put("areacode", ad.getTown());
		    	}else if(ad.getDistrict()!=null && !ad.getDistrict().equals("")){
		    		map.put("areaname", "district");
			    	map.put("areacode", ad.getDistrict());
		    	}else if(ad.getCity()!=null && !ad.getCity().equals("")){
		    		map.put("areaname", "city");
			    	map.put("areacode", ad.getCity());
		    	}else if(ad.getProvince()!=null && !ad.getProvince().equals("")){
		    		if(!ad.getProvince().equals("00000000")){
		    			map.put("areaname", "province");
				    	map.put("areacode", ad.getProvince());
		    		}
		    	}
		    	Map<String, String> urlmap = new HashMap<String, String>();
		    	urlmap.put("action", "send");
		    	//PushController.sendPost("http://47.94.166.156:8080/androidpnserver/notification.do?", urlmap, map);
		    	PushController.sendPost("http://127.0.0.1:8080/notification.do?", urlmap, map);
			}
		}else{
			adResult.setMsg("修改失败");
			adResult.setStatus(0);
		}
		return adResult;
	}

	@Override
	public AdResult adIsBad(Integer adid) {
		AdResult adResult=new AdResult();
		int result=managerDao.updateAdStatusBad(adid);
		if(result>0){
			adResult.setMsg("修改成功");
			adResult.setStatus(1);
		}else{
			adResult.setMsg("修改失败");
			adResult.setStatus(0);
		}
		return adResult;
	}

}
