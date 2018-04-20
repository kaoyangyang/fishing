
package com.sdtz.adlet.service; 

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sdtz.adlet.entity.Store;
import com.sdtz.adlet.util.AdResult;


public interface StoreService {

	
	/**创建我的店
	 * @param store
	 * @return
	 */
	public AdResult createStore (Store store);
	
	/**修改我的店的信息
	 * @param store
	 * @return
	 */
	public AdResult updateStore(Store store,HttpServletRequest request,CommonsMultipartFile[] files);
	
	/**查找我的店
	 * @param user_id
	 * @return
	 */
	public AdResult getStore(int storeid);

	/**修改我的店的信息不含新图片
	 * @param store
	 * @return
	 */
	public AdResult updateStore(Store store,HttpServletRequest request);
	
	/**修改我拍的事业的背景照片
	 * @param store
	 * @return
	 */
	public AdResult updateStoreIcon(Store store,HttpServletRequest request,CommonsMultipartFile file);
}
 