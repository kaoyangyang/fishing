package com.sdtz.adlet.controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sdtz.adlet.entity.Ad;
import com.sdtz.adlet.entity.Addgood;
import com.sdtz.adlet.entity.Comment;
import com.sdtz.adlet.service.AdService;
import com.sdtz.adlet.util.AdResult;

import paramer.GetAdsParamer;
@Controller
public class AdController {

	@Resource
	private AdService adService;
	@RequestMapping("/ad/stickAdFile.do")
	@ResponseBody
	public AdResult publish(Ad ad,HttpServletRequest request,@RequestParam("file") CommonsMultipartFile[] files) throws Exception {
		AdResult result = adService.save(ad,request,files);
		return result;
	}
	//无文件上传模式
	@RequestMapping("/ad/stickAd.do")
	@ResponseBody
    public AdResult publish(Ad ad){
		System.out.println(ad);
		AdResult result=adService.save(ad);
		return result;
    }
	//获取广告列表
	@RequestMapping("/ad/getAds.do")
	@ResponseBody
	public AdResult getAds(GetAdsParamer paramer){//如果采用json方式向上提交数据则应在接受参数前加注解@RequestBody
		//System.out.println(paramer.toString());
		AdResult result=null;
		if(paramer.getKeyword().equals("")){
			result=adService.getAds(paramer);
		}else{
			result=adService.getAdsByKeyword(paramer);
		}
		return result;
	}
	
	//获取某条广告内容
	@RequestMapping("/ad/getAdById.do")
	@ResponseBody
	public AdResult getAdById(@RequestBody Integer ad_id) throws Exception {
		System.out.println(ad_id);
		AdResult result=adService.getAdById(ad_id);
		return result;
	}
	//记录广告浏览次数
	@RequestMapping("/ad/recordScan.do")
	@ResponseBody
	public AdResult recordScan(int clicks,int ad_id){
		AdResult result=adService.recordScan(ad_id, ++clicks);
		return result;
	}
	//获取某条广告内容包括user
	@RequestMapping("/ad/getAdInfo.do")
	@ResponseBody
	public AdResult getAdInfo(Integer id) throws Exception {
		System.out.println(id);
		AdResult result=adService.getAdInfo(id);
		return result;
	}
	
	//对广告进行评论
	@RequestMapping("/ad/publishAdComment.do")
	@ResponseBody
	public AdResult publishAdComment(Comment comment){
		//System.out.println(comment.toString());
		AdResult result=adService.publishAdComment(comment);
		return result;
	}
	
	//获取广告的评论
	@RequestMapping("/ad/getCommentsByAdId.do")
	@ResponseBody
	public AdResult getCommentsByAdId(int adid){
		AdResult result=adService.getCommentsByAdId(adid);
		return result;
	}
	
	//发布某条评论的回复
	@RequestMapping("/ad/publishAdCommentReply.do")
	@ResponseBody
	public AdResult publishAdCommentReply(Comment comment){
		//System.out.println(comment);
		int id=comment.getId();
		AdResult result=adService.publishAdCommentReply(comment);
		//如果回复成功则需要将当前评论的回复数量加1
		if(result.getStatus()==1){
			adService.updateAdCommentNumber(id);
		}
		return result;
	}
	//获取某条评论的所有回复
	@RequestMapping("/ad/getCommentReplys.do")
	@ResponseBody
	public AdResult getCommentReplys(int commentid){
		AdResult result=adService.getCommentReplys(commentid);
		return result;
	}
	
	@RequestMapping("/ad/updateCommentGoodnumber.do")
	@ResponseBody
	public AdResult updateCommentGoodnumber(int id,int userid){
		//System.out.println(id+":"+userid);
		AdResult result;
		//先通过用户的id获取点赞实例，再修改点赞的数量
		List<Addgood> addgoods=adService.getAddgoodByUserId(userid);
		if(addgoods.size()>0){
			boolean flag=false;
			//循环判断获取到的点赞实例是否为当前评论的
			for(int i=0;i<addgoods.size();i++){
				Addgood addgood=addgoods.get(i);
				if(addgood.getCommentid()==id){
					flag=true;
					break;
				}
			}
			if(flag){
				result=new AdResult();
				result.setMsg("您已点赞");
				result.setStatus(2);
			}else{
				result=adService.updateCommentGoodnumber(id);
			}
		}else{
			//此用户对当前的评论还未进行点赞操作，并进行点赞操作
			result=adService.updateCommentGoodnumber(id);
		}
		return result;
	}
	
	@RequestMapping("/ad/addAddgood.do")
	@ResponseBody
	public AdResult excuteAddAddgood(Addgood addgood){
		AdResult result=adService.addAddgood(addgood);
		return result;
	}
	
	@RequestMapping("/ad/deleteCommentById.do")
	@ResponseBody
	public AdResult excuteDeleteCommentById(Integer commentreplyid,Integer commentid){
		AdResult result=adService.deleteCommentById(commentreplyid,commentid);
		return result;
	}
	
	
	/*
	 * android端文件上传测试
	 */
	@RequestMapping("/fileupload.do")
	@ResponseBody
	public AdResult testUploadFile(@RequestParam("file") CommonsMultipartFile[] files){
		//System.out.println(ad.toString());
		AdResult result=new AdResult();
		if(files!=null){
			try {
				for(int i=0;i<files.length;i++){
					InputStream in=files[i].getInputStream();
					File file=new File("E:"+File.separator+""+i+"text.png");
					FileOutputStream fos=new FileOutputStream(file);
					int len=-1;
					byte[] bytes=new byte[1024];
					while((len=in.read(bytes))!=-1){
						fos.write(bytes, 0, len);
					}
					in.close();
					fos.close();
				}
				result.setMsg("成功了");
				result.setStatus(0);
				return result;
			} catch (IOException e) {
				e.printStackTrace();
				result.setMsg("失败了");
				result.setStatus(0);
				return result;
				
			}
		}
		result.setMsg("失败了");
		result.setStatus(0);
		return result;
	}
	
}
