package com.okflow.modules.received.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.okflow.common.persistence.BaseDao;
import com.okflow.modules.received.entity.Claban;

/**
 * 班级DAO接口
 * 
 * @author xiaofanglin
 *
 */
@Repository
public class ClabanDao extends BaseDao<Claban> {

	public List<String> getcNameList() {
		return find("select name from Claban");
	}
}
