package com.okflow.modules.received.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.okflow.modules.received.dao.ConsumerDao;
import com.okflow.modules.received.entity.Consumer;

@Service
@Transactional(readOnly = true)
public class ConsumerService {

	@Autowired
	private ConsumerDao consumerDao;

	public List<Consumer> findByYbId(String ybId) {
		return consumerDao.findByYbId(ybId);
	}

	@Transactional(readOnly = false, timeout = 240)
	public void updateStatus(String id) {
		consumerDao.updateStatus(id);
	}

}
