package com.sdtz.adlet.dao;

import java.util.HashMap;
import java.util.List;

import com.sdtz.adlet.entity.Ad;
import com.sdtz.adlet.entity.Addgood;
import com.sdtz.adlet.entity.Comment;

public interface AdDao {

	//保存广告
	public int save(Ad ad);

	//获取广告列表
	public List<Ad> getAdsByCountry(HashMap<String ,Object> params);
	public List<Ad> getAdsByProvince(HashMap<String ,Object> params);
	public List<Ad> getAdsByCity(HashMap<String ,Object> params);
	public List<Ad> getAdsByDistrict(HashMap<String ,Object> params);
	public List<Ad> getAdsByTown(HashMap<String ,Object> params);
	
	public List<Ad> getAdsByCountryType(HashMap<String, Object> params);
	public List<Ad> getAdsByProvinceType(HashMap<String, Object> params);
	public List<Ad> getAdsByCityType(HashMap<String, Object> params);
	public List<Ad> getAdsByDistrictType(HashMap<String, Object> params);
	public List<Ad> getAdsByTownType(HashMap<String, Object> params);
	
	public List<Ad> getAdsAuto(HashMap<String, Object> params);
	public List<Ad> getAdsAutoType(HashMap<String, Object> params);
	//获取某条广告详情
	public Ad getAdById(int ad_id);
	//记录广告浏览次数
	public int recordScan(HashMap<String ,Integer> params);

	/** 
	* @Description:获取某条广告信息包括user信息
	* @author dengjb 
	* @date 2017-10-18 上午10:51:09 
	*/
	public Ad getAdInfo(int id);
	
	/**发布一条广告的评论
	 * @param comment
	 * @author yedong
	 * @return 
	 */
	public int publishAdComment(Comment comment);
	
	/**获取本条广告的所有评论
	 * @param adid
	 * @return 返回评论实例（Comment）的集合 
	 */
	public List<Comment> getCommentsByAdId(int adid);
	
	/**对某条评论进行回复
	 * @param commentid某条评论的id
	 * @return 
	 */
	public int publishAdCommentReply(Comment comment);
	
	/**修改某条评论的回复数量(增加1)
	 * @param id
	 * @return
	 */
	public int updateAdCommentNumber(int id);
	
	
	/**评论回复数量减1
	 * @param id
	 * @return
	 */
	public int reduceAdCommentNumber(int id);
	
	/**获取评论的回复
	 * @param commentid评论的id
	 * @return
	 */
	public List<Comment> getCommentReplys(int commentid);
	
	/**对某条评论进行点赞后修改点赞数量
	 * @param id
	 * @return 返回影响的条目数量
	 */
	public int updateCommentGoodnumber(int id);
	
	/**通过用户的id点赞的实例
	 * @param commentid
	 * @return
	 */
	public List<Addgood> getAddgoodByUserId(int userid);
	
	/**添加一条点赞
	 * @param addgood
	 * @return
	 */
	public int addAddgood(Addgood addgood);
	
	/**通过关键字来获取广告
	 * @param params
	 * @return
	 */
	public List<Ad> getAdsByKeyword(HashMap<String, Object> params);
	
	public List<Ad> getCountryAdsByKeyword(HashMap<String, Object> params);
	
	public List<Ad> getProvinceAdsByKeyword(HashMap<String, Object> params);
	
	public List<Ad> getCityAdsByKeyword(HashMap<String, Object> params);
	
	public List<Ad> getDistrictAdsByKeyword(HashMap<String, Object> params);
	
	public List<Ad> getTownAdsByKeyword(HashMap<String, Object> params);
	
	
	/**获取广告的第二种方式
	 * @param params
	 * @return
	 */
	public List<Ad> getAdsBySecondType(HashMap<String, Object> params);
	
	/**获取广告的第三种方式
	 * @param params
	 * @return
	 */
	public List<Ad> getAdsByThirdType(HashMap<String, Object> params);
	
	/**删除自己的某条评论
	 * @param commentid
	 * @return
	 */
	public int deleteCommentById(int commentid);
	
	/**删除当前评论的回复
	 * @param commentid //某条评论的ID号
	 * @return
	 */
	public int deleteReplys(int commentid);
	
	
	
	
	
}
