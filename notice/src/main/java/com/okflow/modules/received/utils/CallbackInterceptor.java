package com.okflow.modules.received.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.yiban.open.Authorize;
import cn.yiban.open.common.User;
import net.sf.json.JSONObject;

public class CallbackInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (request.getSession().getAttribute(QueueUtils.currentUser) == null) {
			if (request.getParameter("code") != null) {
				String code = request.getParameter("code");
				Authorize authorize = new Authorize(AppUtil.AppID, AppUtil.AppSECRET);
				JSONObject json = JSONObject.fromObject(authorize.querytoken(code, AppUtil.BACKURL));
				String access_token = json.getString("access_token");
				User user = new User(access_token);
				request.getSession().setAttribute(QueueUtils.currentUser, user);
				response.sendRedirect(request.getContextPath() + "/a/loading");
			} else {
				QueueUtils.authenTication(response);
			}
		} else {
			return true;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
