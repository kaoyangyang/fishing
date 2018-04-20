package com.sdtz.adlet.service;

import com.sdtz.adlet.util.AdResult;

public interface AdTypeService {

	/**获取所有广告的编号和名称
	 * @return
	 */
	public AdResult getFirstAdType();
	
	/**
	 * 获取某个主类广告的次类
	 * @return
	 */
	public AdResult getSecondAdType(int firsttype);
}
