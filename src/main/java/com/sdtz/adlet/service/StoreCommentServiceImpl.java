package com.sdtz.adlet.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sdtz.adlet.dao.StoreCommentDao;
import com.sdtz.adlet.entity.StoreComment;
import com.sdtz.adlet.entity.StoreCommentReply;
import com.sdtz.adlet.entity.Storegood;
import com.sdtz.adlet.util.AdResult;
@Service("storeCommentService")
public class StoreCommentServiceImpl implements StoreCommentService{

	@Resource
	private StoreCommentDao storeCommentDao;
	
	@Override
	public AdResult publishStoreComment(StoreComment storeComment) {
		AdResult result=new AdResult();
		storeComment.setAddtime(new Date());
		int resultcode=storeCommentDao.publishStoreComment(storeComment);
		if(resultcode>0){
			result.setMsg("发布成功");
			result.setStatus(1);
			//返回评论对象
			storeComment.setCommentnumber(0);
			storeComment.setGoodnumber(0);
			result.setData(storeComment);
		}else{
			result.setMsg("发布失败");
			result.setStatus(0);
		}
		return result;
	}

	@Override
	public AdResult getStoreComments(int storeid) {
		AdResult result=new AdResult();
		List<StoreComment> storeComments = storeCommentDao.getStoreCommentsById(storeid);
		if(storeComments!=null){
			result.setData(storeComments);
			result.setMsg("获取留言成功");
			result.setStatus(1);
		}else{
			result.setMsg("获取留言失败");
			result.setStatus(0);
		}
		return result;
	}

	@Override
	public AdResult publishStoreCommentReply(StoreCommentReply storeCommentReply) {
		AdResult result=new AdResult();
		storeCommentReply.setAddtime(new Date());
		int resultcode = storeCommentDao.publishStoreCommentReply(storeCommentReply);
		if(resultcode>0){
			//调用修改留言回复的接口
			int resultnumber=storeCommentDao.updateStoreCommentNumber(storeCommentReply.getCommentid());
			if(resultnumber>0){
				result.setMsg("发布成功");
				result.setStatus(1);
			}else{
				result.setMsg("发布失败");
				result.setStatus(0);
			}
		}else{
			result.setMsg("发布失败");
			result.setStatus(0);
		}
		return result;
	}

	@Override
	public AdResult getStoreCommentReplys(int commentid) {
		AdResult result=new AdResult();
		List<StoreCommentReply> storeCommentReplys=storeCommentDao.getStoreCommentReplys(commentid);
		if(storeCommentReplys!=null){
			result.setData(storeCommentReplys);
			result.setMsg("获取留言回复成功");
			result.setStatus(1);
		}else{
			result.setMsg("获取留言回复失败");
			result.setStatus(0);
		}
		return result;
	}

	@Override
	public AdResult addStoregood(Storegood storegood) {
		AdResult result=new AdResult();
		int resultcode=storeCommentDao.addStoregood(storegood);
		if(resultcode>0){
			//修改点赞数量
			int code = storeCommentDao.updateCommentGoodnumber(storegood.getCommentid());
			if(code>0){
				result.setMsg("点赞成功");
				result.setStatus(1);
			}else{
				result.setMsg("点赞失败");
				result.setStatus(0);
			}
		}else{
			result.setMsg("点赞失败");
			result.setStatus(0);
		}
		return result;
	}

	@Override
	public AdResult updateCommentGoodnumber(int commentid,int userid) {
		AdResult result=new AdResult();
		//先获取所有对此留言的点赞
		Map<String, Integer> map=new HashMap<String,Integer>();
		map.put("commentid", commentid);
		map.put("userid", userid);
		Storegood storegood=storeCommentDao.getStoregoodByUserId(map);
		if(storegood!=null){
			//说明已经点赞了
			result.setMsg("您已点赞");
			result.setStatus(2);
		}else{
			result.setMsg("可以点赞");
			result.setStatus(1);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)//开启事务
	public AdResult deleteCommentById(int commentid) {
		AdResult result=new AdResult();
		int resultcode=storeCommentDao.deleteCommentById(commentid);
		//删除当前留言的回复留言利用事务处理
		//需要开启事务
		int resultcode2=storeCommentDao.deleteCommentReplys(commentid);
		if(resultcode>0){
			result.setMsg("删除成功");
			result.setStatus(1);
		}else{
			result.setMsg("删除失败");
			result.setStatus(0);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)//开启事务
	public AdResult deleteCommentReply(int id,int commentid) {
		AdResult result=new AdResult();
		//需要开启事务
		//删除某条留言的某条回复
		int resultcode=storeCommentDao.deleteCommentReply(id);
		//将某条留言的回复数量减1
		int resultcode2=storeCommentDao.reduceStoreCommentNumber(commentid);
		if(resultcode>0){
			result.setMsg("删除成功");
			result.setStatus(1);
		}else{
			result.setMsg("删除失败");
			result.setStatus(0);
		}
		return result;
	}

}
