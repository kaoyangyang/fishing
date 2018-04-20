package com.sdtz.adlet.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sdtz.adlet.dao.AdTypeDao;
import com.sdtz.adlet.entity.AdType;
import com.sdtz.adlet.util.AdResult;

@Service("adTypeService")
public class AdTypeServiceImpl implements AdTypeService{

	@Resource
	private AdTypeDao adTypeDao;
	
	@Override
	public AdResult getFirstAdType() {
		AdResult result=new AdResult();
		List<AdType> adTypes=adTypeDao.getFirstAdType();
		if(adTypes!=null){
			result.setStatus(1);
			result.setMsg("获取成功");
			result.setData(adTypes);
		}else{
			result.setStatus(0);
			result.setMsg("获取失败");
		}
		return result;
	}

	@Override
	public AdResult getSecondAdType(int firsttype) {
		AdResult result=new AdResult();
		List<AdType> adTypes=adTypeDao.getSecondAdType(firsttype);
		if(adTypes!=null){
			result.setStatus(1);
			result.setMsg("获取成功");
			result.setData(adTypes);
		}else{
			result.setStatus(0);
			result.setMsg("获取失败");
		}
		return result;
	}

	
}
