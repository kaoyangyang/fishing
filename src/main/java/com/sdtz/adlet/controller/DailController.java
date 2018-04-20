package com.sdtz.adlet.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdtz.adlet.entity.DailRecord;
import com.sdtz.adlet.service.DailService;
import com.sdtz.adlet.util.AdResult;

@Controller
public class DailController {

	@Resource
	private DailService dailService;
	
	@RequestMapping("/dail/dailRecord.do")
	@ResponseBody
	public AdResult excuteDail(DailRecord dailRecord){
		AdResult result=dailService.dailRecord(dailRecord);
		return result;
	}
}
