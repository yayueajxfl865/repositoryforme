package com.okflow.modules.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.*;

/**
 * 系统登录 Controller
 * 
 * @author xiaofanlin
 * @version 210-03
 */
@Controller
@RequestMapping(value = { "${adminPath}/ls/ls" })
public class LoginController {

	@SuppressWarnings("unused")
	@RequestMapping(value = { "login" })
	@ResponseBody
	public String login(String userCode, String passCode) { // 登录系统验证
		System.out.println("userCode" + userCode);
		System.out.println("passCode" + passCode);
		System.out.println("系统是否执行到这儿");
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(userCode) && StringUtils.isNotBlank(passCode)) {
			String userName = null, password = null;
			try {
				userName = URLDecoder.decode(userCode, "utf-8");
				password = URLDecoder.decode(passCode, "utf-8");
				System.out.println("userName：" + userName);
				System.out.println("password：" + password);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else {
			map.put("status", "200");

		}
		return JSON.toJSONString(map);
	}
}
