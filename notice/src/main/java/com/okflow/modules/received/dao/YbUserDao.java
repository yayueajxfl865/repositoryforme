package com.okflow.modules.received.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.okflow.common.persistence.BaseDao;
import com.okflow.common.persistence.Parameter;
import com.okflow.modules.received.entity.YbUser;

/**
 * 易班学生DAO接口
 * 
 * @author xiaofanglin
 *
 */
@Repository
public class YbUserDao extends BaseDao<YbUser> {

	public List<YbUser> findStuList(String yb_userid) {
		return find("from YbUser where yb_userid=:p1", new Parameter(yb_userid));
	}

}
