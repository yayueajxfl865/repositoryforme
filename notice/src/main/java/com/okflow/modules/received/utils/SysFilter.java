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

/**
 * Servlet Filter implementation class SysFilter
 */
public class SysFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public SysFilter() {
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
		if (request.getSession().getAttribute("currentUser") == null) {
			Authorize authorize = new Authorize(AppUtil.AppID, AppUtil.AppSECRET);
			String url = authorize.forwardurl(AppUtil.BACKURL, null, Authorize.DISPLAY_TAG_T.WEB);
			response.sendRedirect(url);
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
