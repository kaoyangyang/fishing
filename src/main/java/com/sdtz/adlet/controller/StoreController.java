package com.sdtz.adlet.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sdtz.adlet.entity.Store;
import com.sdtz.adlet.service.StoreService;
import com.sdtz.adlet.util.AdResult;

@Controller
public class StoreController {

	@Resource
	private StoreService storeService;
	
	@RequestMapping("/store/createStore.do")
	@ResponseBody
	public AdResult createStore(Store store){
		AdResult result=storeService.createStore(store);
		return result;
	}
	
	@RequestMapping("/store/getStore.do")
	@ResponseBody
	public AdResult getStore(Integer storeid){
		AdResult result=storeService.getStore(storeid);
		return result;
	};
	
	@RequestMapping("/store/updateStore.do")
	@ResponseBody
	public AdResult updateStore(Store store,HttpServletRequest request,@RequestParam("file") CommonsMultipartFile[] files){
		//System.out.println(store.toString());
		AdResult result=storeService.updateStore(store,request,files);
		return result;
	}
	
	@RequestMapping("/store/updateStoreNoNewImg.do")
	@ResponseBody
	public AdResult updateStore(Store store,HttpServletRequest request){
		AdResult result=storeService.updateStore(store,request);
		return result;
	}
	
	@RequestMapping("/store/updateStoreIcon.do")
	@ResponseBody
	public AdResult updateStoreIcon(Store store,HttpServletRequest request,@RequestParam("file") CommonsMultipartFile file){
		AdResult result=storeService.updateStoreIcon(store,request,file);
		return result;
	}
}
