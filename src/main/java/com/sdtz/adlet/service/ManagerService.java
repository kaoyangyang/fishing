package com.sdtz.adlet.service;

import java.util.HashMap;

import com.sdtz.adlet.entity.Manager;
import com.sdtz.adlet.util.AdResult;

public interface ManagerService {
	
	/**
	 * 根据管理员的账号即其所管辖的区域的代码获取
	 * 其需要审核的广告
	 * @param acount
	 * @return
	 */
	public AdResult getMyAreaNeedCheckAd(String acount);
	
	/**
	 * 超级管理员获取所有需要审核的广告
	 * @return
	 */
	public AdResult getNeedCheckAd();
	
	/**
	 * 获取管理者的信息
	 * @param manager
	 * @return
	 */
	public AdResult getManagerInfo(Manager manager);
	
	/**
	 * 广告审核通过
	 * @param adid
	 * @return
	 */
	public AdResult adIsOk(Integer adid,Integer ispush);
	
	/**
	 * 广告审核不通过
	 * @param adid
	 * @param ispush
	 * @return
	 */
	public AdResult adIsBad(Integer adid);
}
