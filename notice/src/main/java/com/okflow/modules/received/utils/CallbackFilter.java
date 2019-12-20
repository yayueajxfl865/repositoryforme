package com.okflow.modules.received.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yiban.open.Authorize;
import cn.yiban.open.common.User;
import net.sf.json.JSONObject;

/**
 * Servlet Filter implementation class CallbackFilter
 */

public class CallbackFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public CallbackFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest args1, ServletResponse args2, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) args1;
		HttpServletResponse response = (HttpServletResponse) args2;
		if (request.getSession().getAttribute(QueueUtils.currentUser) == null) {
			if (request.getParameter("code") != null) {
				String code = request.getParameter("code");
				Authorize authorize = new Authorize(AppUtil.AppID, AppUtil.AppSECRET);
				JSONObject json = JSONObject.fromObject(authorize.querytoken(code, AppUtil.BACKURL));
				String access_token = json.getString("access_token");
				User user = new User(access_token);
				request.getSession().setAttribute(QueueUtils.currentUser, user);
				response.sendRedirect(request.getContextPath() + "/a/queue/queue/tokenUrl");
			} else {
				QueueUtils.authenTication(response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
