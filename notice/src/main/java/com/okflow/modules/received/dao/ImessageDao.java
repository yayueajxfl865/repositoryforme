package com.okflow.modules.received.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.okflow.common.persistence.BaseDao;
import com.okflow.common.persistence.Parameter;
import com.okflow.modules.received.entity.Imessage;

/**
 * 消息DAO接口
 * 
 * @author xiaofanglin
 *
 */
@Repository
public class ImessageDao extends BaseDao<Imessage> {

	@SuppressWarnings("unchecked")
	public List<Imessage> getMeNewsList() {
		String hql = "from Imessage order by createDate desc";
		List<Imessage> list = createQuery(hql).setMaxResults(7).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Imessage> getMePageList(Integer pageNo) {// 分页查询通知
		String hql = "from Imessage order by createDate desc";
		List<Imessage> list = createQuery(hql).setFirstResult((pageNo - 1) * 6).setMaxResults(6).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Imessage> getmyMePageList(String yb_userid, Integer pageNo) {// 分页查询通知-查询我的发布
		String hql = "from Imessage where ybid=:p1 order by createDate desc";
		List<Imessage> list = createQuery(hql, new Parameter(yb_userid)).setFirstResult((pageNo - 1) * 6)
				.setMaxResults(6).list();
		return list;
	}

	public void updatefszt(String id) {// 修改发送状态为撤回
		update("update Imessage set fszt='1' where id=:p1", new Parameter(id));
	}
}
