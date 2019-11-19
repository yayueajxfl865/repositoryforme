package com.okflow.modules.received.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.okflow.modules.received.dao.ConsumerDao;
import com.okflow.modules.received.dao.ImessageDao;
import com.okflow.modules.received.dao.ProducerDao;
import com.okflow.modules.received.dao.YbUserDao;
import com.okflow.modules.received.entity.Consumer;
import com.okflow.modules.received.entity.Imessage;
import com.okflow.modules.received.entity.Producer;
import com.okflow.modules.received.entity.YbUser;

/**
 * 消息Service
 * 
 * @author xiaofanglin
 *
 */
@Service
@Transactional(readOnly = true)
public class ImessageService {

	@Autowired
	private ProducerDao producerDao;
	@Autowired
	private ConsumerDao consumerDao;
	@Autowired
	private ImessageDao imessageDao;
	@Autowired
	private YbUserDao ybUserDao;

	public List<Imessage> getMeNewsList() {
		return imessageDao.getMeNewsList();
	}

	public Imessage get(String id) {
		return imessageDao.get(id);
	}

	@Transactional(readOnly = false, timeout = 240)
	public void saveImessage(String producerKey, String theme, String content, String rever, String consumerKey) {// 保存消息
		Producer producer = new Producer();
		List<YbUser> userList = ybUserDao.findStuList(producerKey);
		producer.setYb_userid(producerKey);
		if (userList.size() > 0) {
			producer.setYbUser(userList.get(0));
		}
		producerDao.save(producer);// 保存消息生产者
		Imessage imessage = new Imessage();
		imessage.setProducer(producer);
		imessage.setTheme(theme);
		imessage.setContent(content);
		imessage.setRever(rever);
		imessageDao.save(imessage);// 保存消息
		List<String> result = Arrays.asList(consumerKey.split(","));// 消费者列表
		for (String key : result) {
			Consumer consumer = new Consumer();
			consumer.setYb_userid(key);
			consumer.setImessage(imessage);
			List<YbUser> uList = ybUserDao.findStuList(key);
			if (uList.size() > 0) {
				consumer.setYbUser(uList.get(0));
			}
			consumerDao.save(consumer);
		}
	}
}
