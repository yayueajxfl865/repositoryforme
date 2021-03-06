package com.okflow.modules.received.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.okflow.modules.received.dao.ClabanDao;
import com.okflow.modules.received.dao.ClubsDao;
import com.okflow.modules.received.dao.GradeDao;
import com.okflow.modules.received.dao.TieDao;
import com.okflow.modules.received.dao.YbUserDao;
import com.okflow.modules.received.entity.Claban;
import com.okflow.modules.received.entity.Clubs;
import com.okflow.modules.received.entity.Grade;
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
	@Autowired
	private ClubsDao clubsDao;
	@Autowired
	private GradeDao gradeDao;

	@Transactional(readOnly = false, timeout = 240)
	public void impStuData(List<Map<String, Object>> sourceList) {// 导入学生数据处理
		tieDao.clear();
		for (Map<String, Object> map : sourceList) {// 遍历List
			String ybID = map.get("0").toString();// 易班ID
			String yb_realname = map.get("1").toString();// 姓名
			String tieName = map.get("2").toString();// 系别
			String clabanName = map.get("3").toString();// 班别
			Tie tie = null;
			Claban claban = null;
			YbUser ybUser = null;
			if (tieDao.getIdList(tieName).size() > 0) {// 包含
				List<Tie> tieList = tieDao.getTieListByName(tieName);
				tie = tieList.get(0);
			} else {
				tie = new Tie();// 创建一个系别
				tie.setName(tieName);
				tieDao.save(tie);// 保存系别
			}
			if (clabanDao.getIdList(clabanName).size() > 0) {// 包含
				List<Claban> clabanList = clabanDao.getClabanListByName(clabanName);
				claban = clabanList.get(0);
			} else {
				claban = new Claban();// 创建一个班别
				claban.setName(clabanName);
				claban.setTie(tie);
				clabanDao.save(claban);// 保存班别
			}
			if (ybUserDao.findStuList(ybID).size() > 0) {// 当前用户已存在系统
				List<YbUser> ybUserList = ybUserDao.findStuList(ybID);
				ybUser = ybUserList.get(0);
			} else {
				ybUser = new YbUser();
				ybUser.setYb_userid(ybID);
				ybUser.setYb_realname(yb_realname);
				ybUser.setClaban(claban);
				ybUserDao.save(ybUser);// 保存人员

			}
		}
	}

	@Transactional(readOnly = false, timeout = 240)
	public void impTeaData(List<Map<String, Object>> sourceList) {// 导入教师数据处理
		ybUserDao.clear();
		for (Map<String, Object> map : sourceList) {// 遍历List
			String ybID = map.get("0").toString();// 易班ID
			String yb_realname = map.get("1").toString();// 姓名
			String role = "teacher";// 角色为教师
			YbUser ybUser = new YbUser();
			ybUser.setYb_userid(ybID);
			ybUser.setRole(role);
			ybUser.setYb_realname(yb_realname);
			ybUserDao.save(ybUser);// 保存教师信息
			Grade grade = new Grade();
			grade.setYb_userid(ybID);
			grade.setRole(role);
			gradeDao.save(grade);// 保存到角色表
		}
	}

	@Transactional(readOnly = false, timeout = 240)
	public void impClubData(List<Map<String, Object>> sourceList) {// 导入社团数据处理
		tieDao.clear();
		for (Map<String, Object> map : sourceList) {// 遍历List
			String ybID = map.get("0").toString();// 易班ID
			String yb_realname = map.get("1").toString();// 姓名
			String tieName = map.get("2").toString();// 系别
			String clabanName = map.get("3").toString();// 班别
			String clubsName = map.get("4").toString();// 社团名称
			Tie tie = null;
			Claban claban = null;
			YbUser ybUser = null;
			Clubs clubs = null;
			if (tieDao.getIdList(tieName).size() > 0) {// 包含
				List<Tie> tieList = tieDao.getTieListByName(tieName);
				tie = tieList.get(0);
			} else {
				tie = new Tie();// 创建一个系别
				tie.setName(tieName);
				tieDao.save(tie);// 保存系别
			}
			if (clabanDao.getIdList(clabanName).size() > 0) {// 包含
				List<Claban> clabanList = clabanDao.getClabanListByName(clabanName);
				claban = clabanList.get(0);
			} else {
				claban = new Claban();// 创建一个班别
				claban.setName(clabanName);
				claban.setTie(tie);
				clabanDao.save(claban);// 保存班别
			}
			if (clubsDao.getClubsList(clubsName).size() > 0) {// 当前社团已存在
				List<Clubs> clubsList = clubsDao.getClubsList(clubsName);
				clubs = clubsList.get(0);
			} else {
				clubs = new Clubs();
				clubs.setClubsName(clubsName);
				clubsDao.save(clubs);
			}
			if (ybUserDao.findStuList(ybID).size() > 0) {// 当前用户已存在系统
				List<YbUser> ybUserList = ybUserDao.findStuList(ybID);
				ybUser = ybUserList.get(0);
				ybUser.setClubs(clubs);
			} else {
				ybUser = new YbUser();
				ybUser.setYb_userid(ybID);
				ybUser.setYb_realname(yb_realname);
				ybUser.setClaban(claban);
				ybUser.setClubs(clubs);
				ybUserDao.save(ybUser);// 保存人员
			}
		}
	}

	@Transactional(readOnly = false, timeout = 240)
	public void authorize(String id, String role) {// 授权角色
		Grade grade = new Grade();
		grade.setYb_userid(ybUserDao.get(id).getYb_userid());
		grade.setRole(role);
		gradeDao.save(grade);
		if ("monitor".equals(role)) {// 授权角色是班长
			ybUserDao.updateS1(id, "1");
		} else if ("chairman".equals(role)) {// 授权角色是会长
			ybUserDao.updateS2(id, "1");
		} else if ("handle".equals(role)) {// 授权角色是经办
			ybUserDao.updateS3(id, "1");
		}
	}

	@Transactional(readOnly = false, timeout = 240)
	public void cancelAuthorize(String id, String role) {// 取消授权角色
		if ("monitor".equals(role)) {// 授权角色是班长
			ybUserDao.updateS1null(id);
		} else if ("chairman".equals(role)) {// 授权角色是会长
			ybUserDao.updateS2null(id);
		} else if ("handle".equals(role)) {
			ybUserDao.updateS3null(id);
		}
		gradeDao.deleteRole(ybUserDao.get(id).getYb_userid());

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

	public List<YbUser> findByRole(String role) {
		return ybUserDao.findByRole(role);
	}

	public YbUser get(String id) {
		return ybUserDao.get(id);
	}

	public List<Object[]> getExsingYbId(String ybId) {
		return ybUserDao.getExsingYbId(ybId);
	}

	public void updateS1(String id, String code) {
		ybUserDao.updateS1(id, code);
	}

	public void updateS2(String id, String code) {
		ybUserDao.updateS2(id, code);
	}

	public List<YbUser> findByS3(String s3) {
		return ybUserDao.findByS3(s3);
	}
}
