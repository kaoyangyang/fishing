package com.sdtz.adlet.dao;

import java.util.List;

import com.sdtz.adlet.entity.AdType;

public interface AdTypeDao {

	public List<AdType> getFirstAdType();
	
	public List<AdType> getSecondAdType(int firsttype);
	
}
