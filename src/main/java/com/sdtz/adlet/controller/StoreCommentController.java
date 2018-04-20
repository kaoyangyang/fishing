package com.sdtz.adlet.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdtz.adlet.entity.StoreComment;
import com.sdtz.adlet.entity.StoreCommentReply;
import com.sdtz.adlet.entity.Storegood;
import com.sdtz.adlet.service.StoreCommentService;
import com.sdtz.adlet.util.AdResult;
@Controller
public class StoreCommentController {

	@Resource
	private StoreCommentService storeCommentService; 
	
	@RequestMapping("/storeComment/publishStoreComment.do")
	@ResponseBody
	public AdResult publishStoreComment(StoreComment storeComment){
		//System.out.println(storeComment);
		AdResult result=storeCommentService.publishStoreComment(storeComment);
		return result;
	}
	
	@RequestMapping("/storeComment/getStoreComments.do")
	@ResponseBody
	public AdResult getStoreComments(Integer storeid){
		AdResult result = storeCommentService.getStoreComments(storeid);
		return result;
	}
	
	@RequestMapping("/storeComment/publishStoreCommentReply.do")
	@ResponseBody
	public AdResult publishStoreCommentReply(StoreCommentReply storeCommentReply){
		AdResult result=storeCommentService.publishStoreCommentReply(storeCommentReply);
		return result;
	}
	
	@RequestMapping("/storeComment/getStoreCommentReplys.do")
	@ResponseBody
	public AdResult getStoreCommentReplys(Integer commentid){
		AdResult result=storeCommentService.getStoreCommentReplys(commentid);
		return result;
	}
	
	@RequestMapping("/storeComment/addStoregood.do")
	@ResponseBody
	public AdResult addStoregood(Storegood storegood){
		AdResult result=storeCommentService.addStoregood(storegood);
		return result;
	}
	
	@RequestMapping("/storeComment/updateStoreCommentGoodnumber.do")
	@ResponseBody
	public AdResult updateCommentGoodnumber(Integer commentid,Integer userid){
		AdResult result=storeCommentService.updateCommentGoodnumber(commentid,userid);
		return result;
	}
	
	@RequestMapping("/storeComment/deleteCommentById.do")
	@ResponseBody
	public AdResult deleteCommentById(Integer commentid){
		AdResult result=storeCommentService.deleteCommentById(commentid);
		return result;
	}
	
	@RequestMapping("/storeComment/deleteCommentReply.do")
	@ResponseBody
	public AdResult deleteCommentReply(Integer id,Integer commentid){
		AdResult result=storeCommentService.deleteCommentReply(id,commentid);
		return result;
	}
	
}
