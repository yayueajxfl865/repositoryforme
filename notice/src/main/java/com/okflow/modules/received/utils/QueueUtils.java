package com.okflow.modules.received.utils;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.okflow.common.utils.SpringContextHolder;
import com.okflow.modules.received.entity.Tie;
import com.okflow.modules.received.entity.YbUser;
import com.okflow.modules.received.service.TieService;
import com.okflow.modules.received.service.YbUserService;

public class QueueUtils {

	private static TieService tieService = SpringContextHolder.getBean(TieService.class);
	private static YbUserService ybUserService = SpringContextHolder.getBean(YbUserService.class);

	public static List<Tie> getTieList() {
		return tieService.findTieList();
	}

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
}
