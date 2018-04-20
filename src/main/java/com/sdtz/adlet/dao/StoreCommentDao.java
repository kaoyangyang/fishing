package com.sdtz.adlet.dao;

import java.util.List;
import java.util.Map;

import com.sdtz.adlet.entity.StoreComment;
import com.sdtz.adlet.entity.StoreCommentReply;
import com.sdtz.adlet.entity.Storegood;

public interface StoreCommentDao {

	/**发布一条事业的评论
	 * @param storeComment
	 * @return
	 */
	public int publishStoreComment(StoreComment storeComment);
	
	/**根据我的事业的id号获取其留言
	 * @param storeid
	 * @return
	 */
	public List<StoreComment> getStoreCommentsById(int storeid);
	
	/**发布对某条留言的回复
	 * @param storeComment
	 * @return
	 */
	public int publishStoreCommentReply(StoreCommentReply storeCommentReply);
	
	/**获取某条留言的回复
	 * @param commentid
	 * @return
	 */
	public List<StoreCommentReply> getStoreCommentReplys(int commentid);
	
	/**修改某条留言的回复数量
	 * @param id
	 * @return
	 */
	public int updateStoreCommentNumber(int commentid);
	
	
	/**对某条留言进行点赞
	 * @param storegood
	 * @return
	 */
	public int addStoregood(Storegood storegood);
	
	/**修改某条留言的点赞数
	 * @param id
	 * @return
	 */
	public int updateCommentGoodnumber(int commentid);
	
	/**获取用户对此留言的点赞
	 * @param userid
	 * @return
	 */
	public Storegood getStoregoodByUserId(Map<String,Integer> map);
	
	
	/**删除某条留言
	 * @param commentid
	 * @return
	 */
	public int deleteCommentById(int commentid);
	
	
	/**删除某条留言的回复
	 * @param commentid 留言的id
	 * @return
	 */
	public int deleteCommentReplys(int commentid);
	
	
	/**删除某条留言的某条回复
	 * @param id  某条回复的id
	 * @return
	 */
	public int deleteCommentReply(int id);
	
	/**修改某条留言的
	 * @param id
	 * @return
	 */
	public int reduceStoreCommentNumber(int id);
}
