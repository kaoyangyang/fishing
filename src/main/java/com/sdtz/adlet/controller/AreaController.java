package com.sdtz.adlet.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdtz.adlet.service.AreaService;
import com.sdtz.adlet.util.AdResult;

@Controller
public class AreaController {

	@Resource
	private AreaService areaService;
	
	@RequestMapping("/area/getArea.do")
	@ResponseBody
	public AdResult getArea(){
		AdResult result=areaService.getArea();
		return result;
	}
}
