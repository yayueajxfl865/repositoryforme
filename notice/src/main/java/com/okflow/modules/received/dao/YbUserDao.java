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

	public List<YbUser> searchpByName(String yb_realname) {
		return find("from YbUser where yb_realname=:p1", new Parameter(yb_realname));
	}

	public List<YbUser> searchpByIdAndName(String yb_userid, String yb_realname) {
		return find("from YbUser where yb_userid=:p1 and yb_realname=:p2", new Parameter(yb_userid, yb_realname));
	}

}
