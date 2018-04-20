package com.sdtz.adlet.service;

import java.util.Map;

import com.sdtz.adlet.entity.StoreComment;
import com.sdtz.adlet.entity.StoreCommentReply;
import com.sdtz.adlet.entity.Storegood;
import com.sdtz.adlet.util.AdResult;

public interface StoreCommentService {

	/**发布事业评论
	 * @param storeComment
	 * @return
	 */
	public AdResult publishStoreComment(StoreComment storeComment);
	
	/**根据我的事业的id号获取其留言
	 * @param storeid
	 * @return
	 */
	public AdResult getStoreComments(int storeid);
	
	
	/**发布对某条留言的回复
	 * @param storeComment
	 * @return
	 */
	public AdResult publishStoreCommentReply(StoreCommentReply storeCommentReply);
	
	/**获取某条留言的回复
	 * @param commentid
	 * @return
	 */
	public AdResult getStoreCommentReplys(int commentid);
	
	
	/**对某条留言点赞
	 * @param storegood
	 * @return
	 */
	public AdResult addStoregood(Storegood storegood);
	
	/**修改某条留言的点赞数
	 * @param id
	 * @return
	 */
	public AdResult updateCommentGoodnumber(int commentid,int userid);
	
	/**删除某条留言
	 * @param commentid
	 * @return
	 */
	public AdResult deleteCommentById(int commentid);
	
	/**删除某条留言的某条回复
	 * @param id 某条回复的id
	 * @return
	 */
	public AdResult deleteCommentReply(int id,int commentid);
	
}
