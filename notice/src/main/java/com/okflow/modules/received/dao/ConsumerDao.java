package com.okflow.modules.received.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.okflow.common.persistence.BaseDao;
import com.okflow.common.persistence.Parameter;
import com.okflow.modules.received.entity.Consumer;

@Repository
public class ConsumerDao extends BaseDao<Consumer> {

	public void deleteByMsId(String id) {// 根据消息外键删除消费者
		update("delete from Consumer where imessage.id=:p1", new Parameter(id));
	}

	public List<Consumer> findByYbId(String ybId) {
		return find("from Consumer where yb_userid=:p1", new Parameter(ybId));

	}

	public void updateStatus(String id) {
		update("update Consumer set status='已查看' where status is null and id=:p1", new Parameter(id));
	}
}
