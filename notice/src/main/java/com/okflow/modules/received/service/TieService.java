package com.okflow.modules.received.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.okflow.modules.received.dao.TieDao;
import com.okflow.modules.received.entity.Tie;

/**
 * 系别Service
 * 
 * @author xiaofanglin
 *
 */
@Service
@Transactional(readOnly = true)
public class TieService {

	@Autowired
	private TieDao tieDao;

	public List<Tie> findTieList() {
		return tieDao.findTieList();
	}
}
