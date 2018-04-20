package com.sdtz.adlet.dao;

import com.sdtz.adlet.entity.Ad;
import com.sdtz.adlet.entity.Manager;

import java.util.List;

public interface ManagerDao {

	/**
	 * 获取待审核的广告
	 * @param acount
	 * @return
	 */
	public List<Ad> getMyAreaNeedCheckAd(String acount);
	
	/**
	 * 获取所有需要审核的广告
	 * @return
	 */
	public List<Ad> getNeedCheckAd();
	
	/**
	 * 获取管理者信息
	 * @param manager
	 * @return
	 */
	public Manager getManagerInfoByAcount(Manager manager);
	
	/**
	 * 修改广告的状态为1(其他用户可见)
	 * @param id
	 * @return
	 */
	public int updateAdStatusOk(Integer id);
	
	/**
	 * 修改广告状态为0(其他用户不可见)
	 * @param id
	 * @return
	 */
	public int updateAdStatusBad(Integer id);
}
