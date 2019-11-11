package com.okflow.modules.received.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.okflow.modules.received.dao.YbUserDao;

/**
 * 易班人员Service
 * @author xiaofanglin
 * @version 2019
 */
@Service
@Transactional(readOnly=true)
public class YbUserService {

	@Autowired
	private YbUserDao ybUserDao;
	
	@Transactional(readOnly=false,timeout=240)
	public void impStuData(List<Map<String, Object>> sourceList) {
		for(Map<String, Object> map:sourceList) {//遍历List
			for (Entry<String, Object> entry : map.entrySet()) {//遍历map里面的每一条数据,完整的人员信息
				
			}
			
		}
		
	}
}
