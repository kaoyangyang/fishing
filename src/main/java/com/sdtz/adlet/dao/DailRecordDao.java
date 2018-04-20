package com.sdtz.adlet.dao;

import com.sdtz.adlet.entity.DailRecord;

public interface DailRecordDao {

	/**
	 * 记录点击电话按钮的行为
	 * @param dailRecord
	 * @return
	 */
	public int recordDail(DailRecord dailRecord);
}
