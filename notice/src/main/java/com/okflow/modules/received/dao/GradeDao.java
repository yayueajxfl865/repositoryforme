package com.okflow.modules.received.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.okflow.common.persistence.BaseDao;
import com.okflow.common.persistence.Parameter;
import com.okflow.modules.received.entity.Grade;

@Repository
public class GradeDao extends BaseDao<Grade> {

	public List<Grade> findGradeByYbId(String ybId) {
		return find("from Grade where yb_userid=:p1", new Parameter(ybId));
	}

}
