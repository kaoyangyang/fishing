package com.sdtz.adlet.dao;

import com.sdtz.adlet.entity.Store;


public interface StoreDao {

	/**创建我的店
	 * @param store
	 * @return
	 */
	public int createStore (Store store);

	/**修改我的店的信息
	 * @param store
	 * @return
	 */
	public int updateStore(Store store);
	
	/**查找我的店
	 * @param user_id
	 * @return
	 */
	public Store getStore(int storeid);
	
	/**更换我的事业的背景照片
	 * @param store
	 * @return
	 */
	public int updateStoreIcon(Store store);
	
}
