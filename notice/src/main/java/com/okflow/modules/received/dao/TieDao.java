package com.okflow.modules.received.dao;

import java.util.List;

import com.okflow.common.persistence.BaseDao;
import com.okflow.modules.received.entity.Tie;

/**
 * 系别DAO接口
 * 
 * @author xiaofanglin
 *
 */
public class TieDao extends BaseDao<Tie> {

	public List<String> gettNameList() {
		return find("select name from Tie");
	}

}
