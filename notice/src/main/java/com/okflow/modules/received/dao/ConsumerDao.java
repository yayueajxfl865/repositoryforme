package com.okflow.modules.received.dao;

import org.springframework.stereotype.Repository;

import com.okflow.common.persistence.BaseDao;
import com.okflow.common.persistence.Parameter;
import com.okflow.modules.received.entity.Consumer;

@Repository
public class ConsumerDao extends BaseDao<Consumer> {

	public void deleteByMsId(String id) {// 根据消息外键删除消费者
		update("delete from Consumer where imessage.id=:p1", new Parameter(id));
	}
}
