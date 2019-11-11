package com.okflow.common.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;

/**
 * 系统初始化
 * 
 * @author xiaofanglin
 * @version 
 */
@Controller
public class GlobalListener implements InitializingBean {
	@Override
	public void afterPropertiesSet() {
		Global.setDevelopmentMode(Global.isDemoMode());
	}
}
