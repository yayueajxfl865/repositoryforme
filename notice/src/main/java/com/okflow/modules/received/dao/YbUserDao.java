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

	public List<YbUser> findByRole(String role) {
		return find("from YbUser where role=:p1", new Parameter(role));
	}

	public List<Object[]> getExsingYbId(String ybId) {
		return find("select id from YbUser where yb_userid=:p1", new Parameter(ybId));
	}

	public void updateS1(String id, String code) {
		update("update YbUser set s1=:p1 where id=:p2", new Parameter(code, id));
	}

	public void updateS2(String id, String code) {
		update("update YbUser set s2=:p1 where id=:p2", new Parameter(code, id));
	}

	public void updateS3(String id, String code) {
		update("update YbUser set s3=:p1 where id=:p2", new Parameter(code, id));
	}

	public void updateS1null(String id) {
		update("update YbUser set s1=null where id=:p1", new Parameter(id));
	}

	public void updateS2null(String id) {
		update("update YbUser set s2=null where id=:p1", new Parameter(id));
	}

	public void updateS3null(String id) {
		update("update YbUser set s3=null where id=:p1", new Parameter(id));
	}

	public List<YbUser> findByS3(String s3) {
		return find("from YbUser where s3=:p1", new Parameter(s3));
	}

}
