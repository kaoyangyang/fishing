
package com.sdtz.adlet.service; 



import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sdtz.adlet.dao.CodeDao;
import com.sdtz.adlet.entity.Code;




@Service("codeService")

public class CodeServiceImpl implements CodeService {
	@Resource
	private CodeDao codeDao;

	public void save(Code code) {
		codeDao.save(code);

	}

	@Override
	public Code findByPhone(HashMap<String, Object> params) {
		return codeDao.findByPhone(params);
	}


}
 