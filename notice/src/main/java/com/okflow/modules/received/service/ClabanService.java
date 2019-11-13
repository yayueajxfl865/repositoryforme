package com.okflow.modules.received.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.okflow.modules.received.dao.ClabanDao;
import com.okflow.modules.received.entity.Claban;

@Service
@Transactional(readOnly = true)
public class ClabanService {

	@Autowired
	private ClabanDao clabanDao;

	public Claban get(String id) {
		return clabanDao.get(id);
	}
}
