package com.okflow.modules.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.okflow.common.persistence.BaseDao;
import com.okflow.common.persistence.Parameter;
import com.okflow.modules.sys.entity.Dict;

/**
 * 数据字典DAO接口
 * 
 * @author xiaofanglin
 * @version 2019-10-04
 *
 */
@Repository
public class DictDao extends BaseDao<Dict> {

	public List<Dict> findAllList() {
		return find("from Dict order by sort");
	}

	public List<String> findTypeList() {
		return find("select type from Dict group by type order by type");
	}

	public Dict getByTypeAndValue(String type, String value) {
		return getByHql("from Dict where type=:p1 and value=:p2 ", new Parameter(type, value));
	}

	public List<Dict> findByType(String type) {
		return find("from Dict where type=:p1 order by sort", new Parameter(type));
	}

}
