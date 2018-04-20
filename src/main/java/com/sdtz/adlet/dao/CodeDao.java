package com.sdtz.adlet.dao;

import java.util.HashMap;

import com.sdtz.adlet.entity.Code;


public interface CodeDao {

	public void save (Code code);
	public Code findByPhone(HashMap<String, Object> params);
}
