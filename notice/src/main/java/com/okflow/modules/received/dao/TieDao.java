package com.okflow.modules.received.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.okflow.common.persistence.BaseDao;
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

}
