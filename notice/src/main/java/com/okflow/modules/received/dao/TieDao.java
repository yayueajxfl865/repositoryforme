package com.okflow.modules.received.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.okflow.common.persistence.BaseDao;
import com.okflow.common.persistence.Parameter;
import com.okflow.modules.received.entity.Tie;

/**
 * 系别DAO接口
 * 
 * @author xiaofanglin
 *
 */
@Repository
public class TieDao extends BaseDao<Tie> {

	public List<String> gettNameList() {
		return find("select name from Tie");
	}

	public List<String> getIdList(String name) {
		return find("select id from Tie where name=:p1", new Parameter(name));
	}

	public List<Tie> getTieListByName(String name) {
		return find("from Tie where name=:p1", new Parameter(name));
	}

	public List<Tie> findTieList() {
		return find("from Tie");
	}

}
