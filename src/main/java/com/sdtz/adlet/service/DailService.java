package com.sdtz.adlet.service;


import com.sdtz.adlet.entity.DailRecord;
import com.sdtz.adlet.util.AdResult;

public interface DailService {

	/**记录点击电话拨号按钮
	 * @param dailRecord
	 * @return
	 */
	public AdResult dailRecord(DailRecord dailRecord);
}
