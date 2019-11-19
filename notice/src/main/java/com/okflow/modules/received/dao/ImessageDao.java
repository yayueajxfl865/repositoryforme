package com.okflow.modules.received.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.okflow.common.persistence.BaseDao;
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
		List<Imessage> list = createQuery(hql).setMaxResults(10).list();
		return list;
	}
}
