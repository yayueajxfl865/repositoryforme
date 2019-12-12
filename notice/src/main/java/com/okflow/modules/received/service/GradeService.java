package com.okflow.modules.received.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.okflow.modules.received.dao.GradeDao;
import com.okflow.modules.received.entity.Grade;

@Service
@Transactional(readOnly = true)
public class GradeService {

	@Autowired
	private GradeDao gradeDao;

	public List<Grade> findGradeByYbId(String ybId) {
		return gradeDao.findGradeByYbId(ybId);
	}

	public List<Object[]> findExsingYbId(String ybId) {
		return gradeDao.findExsingYbId(ybId);
	}
}
