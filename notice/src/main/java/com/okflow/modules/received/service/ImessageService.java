package com.okflow.modules.received.service;

import java.util.Arrays;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.okflow.modules.received.dao.ConsumerDao;
import com.okflow.modules.received.dao.ImessageDao;
import com.okflow.modules.received.dao.YbUserDao;
import com.okflow.modules.received.entity.Consumer;
import com.okflow.modules.received.entity.Imessage;
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

	public List<Imessage> getMePageList(Integer pageNo) {
		return imessageDao.getMePageList(pageNo);
	}

	public List<Imessage> getmyMePageList(String yb_userid, Integer pageNo) {
		return imessageDao.getmyMePageList(yb_userid, pageNo);
	}

	@Transactional(readOnly = false, timeout = 240)
	public void saveImessage(String producerKey, String theme, String content, String rever, String consumerKey) {// 保存消息
		Imessage imessage = new Imessage();
		imessage.setTheme(theme);
		imessage.setContent(content);
		imessage.setRever(rever);
		imessage.setYbid(producerKey);// 保存生产者ybID
		List<YbUser> list = ybUserDao.findStuList(producerKey);
		if (list.size() > 0) {
			imessage.setYbUser(list.get(0));
		}
		imessageDao.save(imessage);// 保存消息
		List<String> result = Arrays.asList(consumerKey.split(","));// 消费者列表
		for (String key : result) {
			Consumer consumer = new Consumer();
			consumer.setYb_userid(key);
			consumer.setImessage(imessage);
			consumer.setStatus("未查看");
			List<YbUser> uList = ybUserDao.findStuList(key);
			if (uList.size() > 0) {
				consumer.setYbUser(uList.get(0));
			}
			consumerDao.save(consumer);
		}
	}

	@Transactional(readOnly = false, timeout = 240)
	public void deleteImessage(String id) {// 删除指定消息
		consumerDao.deleteByMsId(id);
		imessageDao.deleteById(id);
	}

	@Transactional(readOnly = false, timeout = 240)
	public void recallImessage(String id) {// 撤回操作
		consumerDao.deleteByMsId(id);
		imessageDao.updatefszt(id);
	}
}
