package com.okflow.modules.received.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.okflow.common.utils.SpringContextHolder;
import com.okflow.modules.received.entity.Clubs;
import com.okflow.modules.received.entity.Grade;
import com.okflow.modules.received.entity.Tie;
import com.okflow.modules.received.entity.YbUser;
import com.okflow.modules.received.service.ClubsService;
import com.okflow.modules.received.service.GradeService;
import com.okflow.modules.received.service.TieService;
import com.okflow.modules.received.service.YbUserService;

import cn.yiban.open.Authorize;
import cn.yiban.open.common.User;
import net.sf.json.JSONObject;

/**
 * 系统工具类
 * 
 * @author xiaofanglin
 * @version 2019-11-30
 */
public class QueueUtils {

	public final static String currentUser = "currentUser";
	public static String Token = "";
	private static TieService tieService = SpringContextHolder.getBean(TieService.class);
	private static YbUserService ybUserService = SpringContextHolder.getBean(YbUserService.class);
	private static ClubsService clubsService = SpringContextHolder.getBean(ClubsService.class);
	private static GradeService gradeService = SpringContextHolder.getBean(GradeService.class);

	/**
	 * 获取所有系别
	 * 
	 * @return
	 */
	public static List<Tie> getTieList() {
		return tieService.findTieList();
	}

	/**
	 * 获取所有社团机构
	 * 
	 * @return
	 */
	public static List<Clubs> getClubsList() {
		return clubsService.getAllClubsList();
	}

	/**
	 * 消息的消费者简写
	 * 
	 * @param indexStr
	 * @return
	 */
	public static String getReverStr(String indexStr) {
		String rever = "";
		if (StringUtils.isNotBlank(indexStr)) {
			List<String> result = Arrays.asList(indexStr.split(","));
			List<YbUser> yList = ybUserService.findStuList(result.get(0));
			if (yList.size() > 0) {
				if (result.size() > 1) {
					rever = yList.get(0).getYb_realname() + " 等" + result.size() + "人";
				} else {
					rever = yList.get(0).getYb_realname();
				}
			}
			return rever;
		} else {
			return "";
		}
	}

	/**
	 * 登录请求url
	 * 
	 * @param response
	 */
	public static void authenTication(HttpServletResponse response) {
		Authorize authorize = new Authorize(AppUtil.AppID, AppUtil.AppSECRET);
		String url = authorize.forwardurl(AppUtil.BACKURL, null, Authorize.DISPLAY_TAG_T.WEB);
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 返回当前在线易班用户的易班ID
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public static String getyb_userid(HttpServletRequest request, HttpServletResponse response) {
		try {
			User currentUser = (User) request.getSession().getAttribute(QueueUtils.currentUser);
			if (currentUser != null) {
				JSONObject userInfo = JSONObject.fromObject(currentUser.me()).getJSONObject("info");
				String yb_userid = userInfo.getString("yb_userid");// 当前登录易班Id
				return yb_userid;
			} else {
				QueueUtils.authenTication(response);// 请求登录
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 系统当前时间
	 * 
	 * @return
	 */
	public static String currentTime() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = dateFormat.format(now);
		return time;
	}

	public static String getRole(String ybId) {
		List<Grade> list = gradeService.findGradeByYbId(ybId);
		if (list.size() > 0) {
			return list.get(0).getRole();
		} else {
			return null;
		}
	}

}
