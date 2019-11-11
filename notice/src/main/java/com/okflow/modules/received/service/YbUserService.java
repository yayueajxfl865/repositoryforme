package com.okflow.modules.received.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.okflow.modules.received.dao.ClabanDao;
import com.okflow.modules.received.dao.TieDao;
import com.okflow.modules.received.dao.YbUserDao;
import com.okflow.modules.received.entity.Claban;
import com.okflow.modules.received.entity.Tie;
import com.okflow.modules.received.entity.YbUser;

/**
 * 易班人员Service
 * 
 * @author xiaofanglin
 * @version 2019
 */
@Service
@Transactional(readOnly = true)
public class YbUserService {

	@Autowired
	private YbUserDao ybUserDao;
	@Autowired
	private TieDao tieDao;
	@Autowired
	private ClabanDao clabanDao;

	@Transactional(readOnly = false, timeout = 240)
	public void impStuData(List<Map<String, Object>> sourceList) {
		List<String> tList = tieDao.gettNameList();// 系别集合
		List<String> cList = clabanDao.getcNameList();// 班别集合
		for (Map<String, Object> map : sourceList) {// 遍历List
			String ybID = map.get("ybID").toString();// 易班ID
			String yb_realname = map.get("name").toString();// 姓名
			String tieName = map.get("tie").toString();// 系别
			String clabanName = map.get("claban").toString();// 班别
			Tie tie = null;
			Claban claban = null;
			if (tList.contains(tieName)) {// 包含
				List<Tie> tieList = tieDao.getTieListByName(tieName);
				if (tieList.size() > 0) {
					tie = tieList.get(0);
				}
			} else {
				tie = new Tie();// 创建一个系别
				tie.setName(tieName);
			}
			if (cList.contains(clabanName)) {// 包含
				List<Claban> clabanList = clabanDao.getClabanListByName(clabanName);
				if (clabanList.size() > 0) {
					claban = clabanList.get(0);
				}
			} else {
				claban = new Claban();// 创建一个班别
				claban.setName(clabanName);
				claban.setTie(tie);
			}
			YbUser ybUser = new YbUser();
			ybUser.setYb_userid(ybID);
			ybUser.setYb_realname(yb_realname);
			ybUser.setClaban(claban);
			clabanDao.save(claban);// 保存班别
			ybUserDao.save(ybUser);// 保存人员

		}

	}
}
