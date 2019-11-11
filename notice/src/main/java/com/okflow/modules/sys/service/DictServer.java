package com.okflow.modules.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.okflow.modules.sys.dao.DictDao;
import com.okflow.modules.sys.entity.Dict;

/**
 * 数据字典Service
 * 
 * @author xiaofanglin
 * @version 2019-10-04
 */
@Service
@Transactional(readOnly = true)
public class DictServer {

	@Autowired
	private DictDao dictDao;

	public Dict get(String id) {
		return dictDao.get(id);
	}

}
