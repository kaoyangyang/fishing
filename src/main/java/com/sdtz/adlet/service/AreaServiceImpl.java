package com.sdtz.adlet.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sdtz.adlet.dao.AreaDao;
import com.sdtz.adlet.entity.Area;
import com.sdtz.adlet.util.AdResult;

@Service
public class AreaServiceImpl implements AreaService{

	@Resource
	private AreaDao areaDao;
	
	@Override
	public AdResult getArea() {
		AdResult result=new AdResult();
		List<Area> areas=areaDao.getArea();
		if(areas!=null && areas.size()>0){
			//说明有数据
			result.setData(areas);
			result.setMsg("获取成功");
			result.setStatus(1);
		}else{
			result.setMsg("获取失败");
			result.setStatus(0);
		}
		return result;
	}

}
