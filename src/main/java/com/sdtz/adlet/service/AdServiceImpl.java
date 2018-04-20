package com.sdtz.adlet.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sdtz.adlet.dao.AdDao;
import com.sdtz.adlet.entity.Ad;
import com.sdtz.adlet.entity.AdManager;
import com.sdtz.adlet.entity.Addgood;
import com.sdtz.adlet.entity.Comment;
import com.sdtz.adlet.util.AdResult;
import com.sdtz.adlet.util.CompressImageUtil;
import com.sdtz.adlet.util.ConstantValue;
import com.sdtz.adlet.util.GenerateUniqueId;

import paramer.GetAdsParamer;

@Service
public class AdServiceImpl implements AdService {

	@Resource
	private AdDao addao;
	
	@Override
	public AdResult save(Ad ad) {
		AdResult result = new AdResult();
		ad.setPull_time(new Timestamp(new Date().getTime()));
		ad.setAd_id(GenerateUniqueId.generateUniqueId());
	    //将广告对象中为null的值设置成为""
//		if(ad.getCountry()==null){
//			ad.setCountry("");
//		}
//		if(ad.getProvince()==null){
//			ad.setProvince("");
//		} 
//		if(ad.getCity()==null){
//			ad.setCity("");
//		}
//		if(ad.getDistrict()==null){
//			ad.setDistrict("");
//		}
//		if(ad.getTown()==null){
//			ad.setTown("");
//		}
		int ad_id = addao.save(ad);
		//获取刚刚生成的ad_id
		//System.out.println("inma");
		if (ad_id > 0) {
			result.setMsg("发布成功！");
			result.setStatus(1);
		}else{
			result.setMsg("发布失败！");
			result.setStatus(0);
		}
		//向审核管理广告的表中插入一条数据
//		AdManager adManager = new AdManager();
//		
//		adManagerDao.save(adManager);
		return result;
	}
	
	
	
	@Override
	public AdResult save(Ad ad, HttpServletRequest request, CommonsMultipartFile[] files) {
		//定义一个结果对象一共返回
		AdResult result = new AdResult();
		// 存储当前广告多张图片的组合地址以","隔开（也是写入数据库的地址）
		String filepaths = "";
		//存储当前广告多张缩略图的组合地址以","隔开（也是写入数据库的地址）
		//String thrumPhotos = "";
		// 获取项目的根目录
		String basePath = request.getSession().getServletContext().getRealPath(File.separator);
		//System.out.println(basePath);
		//保存相应的图片至对应的文件存储目录
		if(files!=null){
			try {
				for(int i=0;i<files.length;i++){
					//定义当前时间
					Date now = new Date();
					InputStream in=files[i].getInputStream();
					String relatedpath = "upload" + File.separator + "adphotos" + File.separator + now.getTime()+files[i].getOriginalFilename();
					//将生成的每一张图片的地址拼到 存储多张图片的字符串的地址中
					if (i == (files.length - 1)) {
						filepaths = filepaths + relatedpath;
					} else {
						filepaths = filepaths + relatedpath + ",";
					}
					File file=new File(basePath+relatedpath);
					FileOutputStream fos=new FileOutputStream(file);
					int len=-1;
					byte[] bytes=new byte[1024];
					while((len=in.read(bytes))!=-1){
						fos.write(bytes, 0, len);
					}
					in.close();
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				result.setMsg("失败了");
				result.setStatus(0);
				return result;
			}
		}
		ad.setPull_time(new Timestamp(new Date().getTime()));
		ad.setPhotos(filepaths);
		int resultcode=addao.save(ad);
		if (resultcode > 0) {
			result.setMsg("发布成功！");
			result.setStatus(1);
		}else{
			result.setMsg("发布失败！");
			result.setStatus(0);
		}
		return result;
	}

	@Override
	public AdResult getAds(GetAdsParamer paramer) {
		String province=paramer.getProvinceCode();
		String city=paramer.getCityCode();
		String district=paramer.getDistrictCode();
		String town=paramer.getTownCode();
		AdResult result = new AdResult();
		List<Ad> ads=null;
		// 定义一个map用于存储用户的请求参数
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("pageIndex", (paramer.getPageIndex() - 1) * paramer.getPageSize());
		params.put("pageSize", paramer.getPageSize());
		params.put("type", paramer.getType());
		params.put("secondtype", paramer.getSecondtype());
		params.put("province", paramer.getProvinceCode());
		params.put("city", paramer.getCityCode());
		params.put("district", paramer.getDistrictCode());
		params.put("town", paramer.getTownCode());
		//System.out.println("请求参数"+paramer);
		/*
		 * 根据广告参数的不同调用不同的接口：
		 * 1、type=0000 时，调用getAds
		 * 2、type!=0000 时且secondtype=0,调用getAdsBytType1，
		 * 3、type!=0000时且secondtype！=0，调用getAdsByType2
		 */
		if(paramer.getMethod().equals(ConstantValue.ISLOCATION)){
			//手机自动获取经纬度进行定位
			params.put("country", "00000000");
			if(paramer.getType()==1){
				ads=addao.getAdsAuto(params);
			}else{
				ads=addao.getAdsAutoType(params);
			}
		}else if(paramer.getType()==1){
			if(province.equals("-1")&&city.equals("-1")&&district.equals("-1")&&town.equals("-1")){
				//国家级
			    //System.out.println(params);
				ads=addao.getAdsByCountry(params);
			}else if(city.equals("-1")&&district.equals("-1")&&town.equals("-1")){
				//省级获取广告
				ads = addao.getAdsByProvince(params);
			}else if(district.equals("-1")&&town.equals("-1")){
				//市级获取广告
				ads = addao.getAdsByCity(params);
			}else if(town.equals("-1")){
				//县级获取广告
				ads = addao.getAdsByDistrict(params);
			}else{
				//镇级获取广告
				ads = addao.getAdsByTown(params);
			}
		}else{
			if(province.equals("-1")&&city.equals("-1")&&district.equals("-1")&&town.equals("-1")){
				//国家级
				ads=addao.getAdsByCountryType(params);
			}else if(city.equals("-1")&&district.equals("-1")&&town.equals("-1")){
				//省级获取广告
				ads = addao.getAdsByProvinceType(params);
			}else if(district.equals("-1")&&town.equals("-1")){
				//市级获取广告
				ads = addao.getAdsByCityType(params);
			}else if(town.equals("-1")){
				//县级获取广告
				ads = addao.getAdsByDistrictType(params);
			}else{
				//镇级获取广告
				ads = addao.getAdsByTownType(params);
			}
		}
		if (ads != null) {
			result.setStatus(1);
			result.setMsg("获取成功");
			result.setData(ads);
		} else {
			result.setStatus(0);
			result.setMsg("获取失败");
		}
		return result;
	}
	
	@Override
	public AdResult getAdsByKeyword(GetAdsParamer paramer) {
		String province=paramer.getProvinceCode();
		String city=paramer.getCityCode();
		String district=paramer.getDistrictCode();
		String town=paramer.getTownCode();
		AdResult result = new AdResult();
		List<Ad> ads;
		// 定义一个map用于存储用户的请求参数
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("pageIndex", (paramer.getPageIndex() - 1) * paramer.getPageSize());
		params.put("pageSize", paramer.getPageSize());
		params.put("type", paramer.getType());
		params.put("secondtype", paramer.getSecondtype());
		params.put("province", paramer.getProvinceCode());
		params.put("city", paramer.getCityCode());
		params.put("district", paramer.getDistrictCode());
		params.put("town", paramer.getTownCode());
		params.put("keyword", paramer.getKeyword());
		//System.out.println(params);
		// 获取列表并返回结果
		if(province.equals("-1")&&city.equals("-1")&&district.equals("-1")&&town.equals("-1")){
			//国家级
			ads=addao.getCountryAdsByKeyword(params);
		}else if(city.equals("-1")&&district.equals("-1")&&town.equals("-1")){
			//省级获取广告
			ads = addao.getProvinceAdsByKeyword(params);
		}else if(district.equals("-1")&&town.equals("-1")){
			//市级获取广告
			ads = addao.getCityAdsByKeyword(params);
		}else if(town.equals("-1")){
			//县级获取广告
			ads = addao.getDistrictAdsByKeyword(params);
		}else{
			//镇级获取广告
			ads = addao.getTownAdsByKeyword(params);
		}
		if (ads != null) {
			result.setStatus(1);
			result.setMsg("获取成功");
			result.setData(ads);
		} else {
			result.setStatus(0);
			result.setMsg("获取失败");
		}
		return result;
	}

	@Override
	public AdResult getAdById(int ad_id) {
		AdResult result = new AdResult();
		// 获取某条广告
		Ad ad = addao.getAdById(ad_id);
		if (ad != null) {
			result.setStatus(1);
			result.setMsg("获取成功");
			result.setData(ad);
		} else {
			result.setStatus(0);
			result.setMsg("获取失败");
		}
		return result;
	}

	@Override
	public AdResult recordScan(int ad_id, int clicks) {
		AdResult result = new AdResult();
		HashMap<String, Integer> params = new HashMap<String, Integer>();
		params.put("ad_id", ad_id);
		params.put("clicks", clicks);
		int i = addao.recordScan(params);
		if (i > 0) {
			result.setMsg("记录成功");
			result.setStatus(1);
		} else {
			result.setMsg("记录失败");
			result.setStatus(0);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)//开启事务
	public AdResult getAdInfo(int id) {
		AdResult result = new AdResult();
		// 获取某条广告
		Ad ad = addao.getAdInfo(id);
		System.out.println("id="+id);
		if (ad != null) {
			List<Comment> comments=addao.getCommentsByAdId(id);
			if(comments!=null){
				ad.setComments(comments);
			}
			//System.out.println("手机号:" + ad.getUser().getPhone());
			result.setStatus(1);
			result.setMsg("获取成功");
			result.setData(ad);
		} else {
			result.setStatus(0);
			result.setMsg("获取失败");
		}
		return result;
	}

	@Override
	public AdResult publishAdComment(Comment comment) {
		AdResult result = new AdResult();
		// 发布一条评论,返回结果码
		// 设置评论发布时间
		comment.setAddtime(new Date());
		int resultcode = addao.publishAdComment(comment);
		if (resultcode > 0) {// 表示插入数据库成功
			result.setStatus(1);
			result.setMsg("发布成功");
		} else {// 失败
			result.setStatus(0);
			result.setMsg("发布失败");
		}
		return result;
	}

	@Override
	public AdResult getCommentsByAdId(int adid) {
		AdResult result = new AdResult();
		// 用于存储评论实例
		List<Comment> comments = new ArrayList<Comment>();
		// 获取所有评论
		comments = addao.getCommentsByAdId(adid);
		if (comments != null) {// 表示评论至少有一条
			result.setData(comments);
			result.setMsg("获取评论成功");
			result.setStatus(1);
		} else {
			result.setMsg("获取评论失败");
			result.setStatus(0);
		}
		return result;
	}

	@Override
	public AdResult publishAdCommentReply(Comment comment) {
		AdResult result = new AdResult();
		// 发布一条评论的回复,返回结果码
		// 设置评论发布时间
		comment.setAddtime(new Date());
		int resultcode = addao.publishAdCommentReply(comment);
		if (resultcode > 0) {// 表示插入数据库成功
			result.setStatus(1);
			result.setMsg("发布成功");
		} else {// 失败
			result.setStatus(0);
			result.setMsg("发布失败");
		}
		return result;
	}

	@Override
	public AdResult updateAdCommentNumber(int id) {
		AdResult result = new AdResult();
		int resultcode = addao.updateAdCommentNumber(id);
		if (resultcode > 0) {
			result.setStatus(1);
			result.setMsg("发布成功");
		} else {
			result.setStatus(0);
			result.setMsg("发布失败");
		}
		return result;

	}

	@Override
	public AdResult getCommentReplys(int commentid) {
		AdResult result = new AdResult();
		// 获取某条评论的回复
		List<Comment> commentreplys = addao.getCommentReplys(commentid);
		if (commentreplys != null) {
			result.setData(commentreplys);
			result.setMsg("获取评论回复成功");
			result.setStatus(1);
		} else {
			result.setMsg("获取评论回复失败");
			result.setStatus(0);
		}
		return result;
	}

	@Override
	public AdResult updateCommentGoodnumber(int id) {
		AdResult result = new AdResult();
		// 创建一个map存储
		// Map<String,Integer> map=new HashMap<String,Integer>();
		// map.put("id", id);
		// map.put("userid", userid);
		int resultcode = addao.updateCommentGoodnumber(id);
		if (resultcode > 0) {
			result.setMsg("修改点赞数量成功");
			result.setStatus(1);
		} else {
			result.setMsg("修改点赞数量失败");
			result.setStatus(0);
		}
		return result;
	}

	@Override
	public List<Addgood> getAddgoodByUserId(int userid) {
		List<Addgood> addgoods = addao.getAddgoodByUserId(userid);
		return addgoods;
	}

	@Override
	public AdResult addAddgood(Addgood addgood) {
		AdResult result = new AdResult();
		int resultcode = addao.addAddgood(addgood);
		if (resultcode > 0) {
			result.setMsg("点赞成功");
			result.setStatus(1);
		} else {
			result.setMsg("点赞失败");
			result.setStatus(0);
		}
		return result;
	}

	

	

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)//开启事务
	public AdResult deleteCommentById(int commentreplyid,int commentid) {
		AdResult result=new AdResult();
		if(commentreplyid!=0){
			int resultcode=addao.deleteCommentById(commentreplyid);
			if(resultcode>0){
				result.setMsg("删除成功");
				result.setStatus(1);
				//System.out.println(commentid);
				int resultcode2=addao.reduceAdCommentNumber(commentid);
			}else{
				result.setMsg("删除失败");
				result.setStatus(0);
			}
		}else{
			//删除当前评论
			int resultcode=addao.deleteCommentById(commentid);
			//删除对此评论的回复
			int resultcode2=addao.deleteReplys(commentid);
			if(resultcode>0){
				result.setMsg("删除成功");
				result.setStatus(1);
			}else{
				result.setMsg("删除失败");
				result.setStatus(0);
			}
		}
		return result;
	}

}
