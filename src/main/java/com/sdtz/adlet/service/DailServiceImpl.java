package com.sdtz.adlet.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sdtz.adlet.dao.DailRecordDao;
import com.sdtz.adlet.entity.DailRecord;
import com.sdtz.adlet.util.AdResult;
@Service
public class DailServiceImpl implements DailService{

	@Resource
	private DailRecordDao dailRecordDao;
	@Override
	public AdResult dailRecord(DailRecord dailRecord) {
		AdResult result=new AdResult();
		//设置点击电话按钮的时间
		Date now=new Date();
		dailRecord.setCalltime(now);
		int resultcode=dailRecordDao.recordDail(dailRecord);
		if(resultcode>0){
			result.setMsg("记录成功");
			result.setStatus(1);
		}else{
			result.setMsg("记录失败");
			result.setStatus(0);
		}
		return result;
	}

}
