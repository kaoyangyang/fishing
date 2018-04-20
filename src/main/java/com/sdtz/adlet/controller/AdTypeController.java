package com.sdtz.adlet.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdtz.adlet.service.AdTypeService;
import com.sdtz.adlet.util.AdResult;

@Controller
public class AdTypeController {

	@Resource
	private AdTypeService adTypeService;
	
	@RequestMapping("/adType/getFirstType.do")
	@ResponseBody
	public AdResult getAdType(){
		AdResult result=adTypeService.getFirstAdType();
		return result;
	}
	
	@RequestMapping("/adType/getSecondType.do")
	@ResponseBody
	public AdResult getSecondType(int firsttype){
		AdResult result=adTypeService.getSecondAdType(firsttype);
		return result;
		
	}
}
