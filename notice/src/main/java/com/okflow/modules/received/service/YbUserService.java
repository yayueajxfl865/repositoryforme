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
	public void impStuData(List<Map<String, Object>> sourceList) {// 导入学生数据处理
		for (Map<String, Object> map : sourceList) {// 遍历List
			String ybID = map.get("ybID").toString();// 易班ID
			String yb_realname = map.get("name").toString();// 姓名
			String tieName = map.get("tie").toString();// 系别
			String clabanName = map.get("claban").toString();// 班别
			Tie tie = null;
			Claban claban = null;
			if (tieDao.getIdList(tieName).size() > 0) {// 包含
				List<Tie> tieList = tieDao.getTieListByName(tieName);
				if (tieList.size() > 0) {
					tie = tieList.get(0);
				}
			} else {
				tie = new Tie();// 创建一个系别
				tie.setName(tieName);
				tieDao.save(tie);// 保存系别
			}
			if (clabanDao.getIdList(clabanName).size() > 0) {// 包含
				List<Claban> clabanList = clabanDao.getClabanListByName(clabanName);
				if (clabanList.size() > 0) {
					claban = clabanList.get(0);
				}
			} else {
				claban = new Claban();// 创建一个班别
				claban.setName(clabanName);
				claban.setTie(tie);
				clabanDao.save(claban);// 保存班别
			}
			YbUser ybUser = new YbUser();
			ybUser.setYb_userid(ybID);
			ybUser.setYb_realname(yb_realname);
			ybUser.setClaban(claban);
			ybUserDao.save(ybUser);// 保存人员
		}
	}

	@Transactional(readOnly = false, timeout = 240)
	public void impTeaData(List<Map<String, Object>> sourceList) {// 导入教师数据处理
		for (Map<String, Object> map : sourceList) {// 遍历List
			String ybID = map.get("ybID").toString();// 易班ID
			String yb_realname = map.get("name").toString();// 姓名
			String role = "teacher";// 角色为教师
			YbUser ybUser = new YbUser();
			ybUser.setYb_userid(ybID);
			ybUser.setYb_realname(yb_realname);
			ybUser.setRole(role);
			ybUserDao.save(ybUser);
		}
	}

	@Transactional(readOnly = false, timeout = 240)
	public void impClubData() {// 导入社团数据处理

	}

	@Transactional(readOnly = false, timeout = 240)
	public int delete(String id) {
		return ybUserDao.deleteById(id);
	}

	public List<YbUser> findStuList(String yb_userid) {
		return ybUserDao.findStuList(yb_userid);
	}

	public List<YbUser> searchpByName(String yb_realname) {
		return ybUserDao.searchpByName(yb_realname);
	}

	public List<YbUser> searchpByIdAndName(String yb_userid, String yb_realname) {
		return ybUserDao.searchpByIdAndName(yb_userid, yb_realname);
	}
}
