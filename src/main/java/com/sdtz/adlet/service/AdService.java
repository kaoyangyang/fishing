package com.sdtz.adlet.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sdtz.adlet.entity.Ad;
import com.sdtz.adlet.entity.Addgood;
import com.sdtz.adlet.entity.Comment;
import com.sdtz.adlet.util.AdResult;

import paramer.GetAdsParamer;

public interface AdService {

	/**张贴广告
	 * @param ad
	 * @return
	 */
	public AdResult save(Ad ad);
	
	
	/**
	 * 张贴广告带有图片资源的方式
	 * @param ad
	 * @param request
	 * @param files
	 * @return
	 */
	public AdResult save(Ad ad,HttpServletRequest request,CommonsMultipartFile[] files);
	
	/**获取广告列表
	 * @param pageIndex
	 * @param pageSize
	 * @param type
	 * @return
	 */
	public AdResult getAds(GetAdsParamer paramer);
	
	/**获取某条广告详情
	 * @param ad_id
	 * @return
	 */
	public AdResult getAdById(int ad_id);
	
	/**记录广告浏览次数
	 * @param ad_id
	 * @param clicks
	 * @return
	 */
	public AdResult recordScan(int ad_id,int clicks);

	/**获取广告详情
	 * @param id
	 * @return
	 */
	public AdResult getAdInfo(int id);
	
	/**对广告进行评论
	 * @param adid
	 * @param userid
	 * @param content
	 * @return 返回一个封装了发布状态和提示信息及相应数据的结果对象
	 */
	public AdResult publishAdComment(Comment comment);
		
	/**获取当前广告的所有评论
	 * @param adid
	 * @return 返回结果对象携带所有评论及查询状态、说明state为0表示失败，为1表示成功
	 */
	public AdResult getCommentsByAdId(int adid);
	
	/**对某条评论进行回复
	 * @param commentid
	 * @return
	 */
	public AdResult publishAdCommentReply(Comment comment);
	
	/**将评论的回复数加1*/
	public AdResult updateAdCommentNumber(int id);
	
	/**获取某条评论的回复
	 * @param id
	 * @return
	 */
	public AdResult getCommentReplys(int commentid);
	
	/**对某条评论点赞
	 * @param id
	 * @return
	 */
	public AdResult updateCommentGoodnumber(int id);
	
	/**通过用户的id获取所有为当前评论点赞的实例
	 * @param userid
	 * @return
	 */
	public List<Addgood> getAddgoodByUserId(int userid);
	
	/**添加一条点赞
	 * @param addgood
	 * @return
	 */
	public AdResult addAddgood(Addgood addgood);

	/**通过关键字来搜索广告
	 * @param pageIndex
	 * @param pageSize
	 * @param type
	 * @param secondtype
	 * @param area
	 * @param keyword
	 * @return
	 */
	public AdResult getAdsByKeyword(GetAdsParamer paramer);
	
	/**删除某条评论
	 * @param commentid
	 * @return
	 */
	public AdResult deleteCommentById(int commentreplyid,int commentid);
}
