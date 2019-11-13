package com.okflow.modules.received.utils;

import java.util.List;

import com.okflow.common.utils.SpringContextHolder;
import com.okflow.modules.received.entity.Tie;
import com.okflow.modules.received.service.TieService;

public class QueueUtils {

	private static TieService tieService = SpringContextHolder.getBean(TieService.class);

	public static List<Tie> getTieList() {
		return tieService.findTieList();
	}
}
