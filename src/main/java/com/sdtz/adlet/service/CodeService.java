
package com.sdtz.adlet.service; 

import java.util.HashMap;

import com.sdtz.adlet.entity.Code;


public interface CodeService {

	public void save (Code code);

	public Code findByPhone(HashMap<String, Object> params);
}
 