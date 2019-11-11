package com.okflow.common.utils;

import java.util.Locale;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import com.google.common.base.Strings;

/**
 * 字符串工具类
 * 
 * @author xiaofanglin
 * @version 
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {
	public static String lowerFirst(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		}
		else {
			return str.substring(0, 1).toLowerCase() + str.substring(1);
		}
	}

	public static String upperFirst(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		}
		else {
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		}
	}

	/** 替换掉HTML标签方法 */
	public static String replaceHtml(String html) {
		if (isBlank(html)) {
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/** 转换为Double类型 */
	public static Double toDouble(Object val) {
		if (val == null) {
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/** 转换为Float类型 */
	public static Float toFloat(Object val) {
		return toDouble(val).floatValue();
	}

	/** 转换为Long类型 */
	public static Long toLong(Object val) {
		return toDouble(val).longValue();
	}

	/** 转换为Integer类型 */
	public static Integer toInteger(Object val) {
		return toLong(val).intValue();
	}

	/** 获得i18n字符串 */
	public static String getMessage(String code, Object[] args) {
		LocaleResolver localLocaleResolver = SpringContextHolder.getBean(LocaleResolver.class);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Locale localLocale = localLocaleResolver.resolveLocale(request);
		return SpringContextHolder.getApplicationContext().getMessage(code, args, localLocale);
	}

	/** 获得用户远程地址 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String remoteAddr = request.getHeader("X-Real-IP");
		if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("X-Forwarded-For");
		}
		else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("Proxy-Client-IP");
		}
		else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}

	/** 是否是基层单位 */
	public static boolean isJcdw(String roles) {
		return (!Strings.isNullOrEmpty(roles)) && (roles.indexOf("基层单位") >= 0 || roles.indexOf("经办") >= 0 || roles.indexOf("初核") >= 0 || roles.indexOf("复核") >= 0);
	}

	/** 是否是区直基层单位 */
	public static boolean isQzJcdw(String roles) {
		return (!Strings.isNullOrEmpty(roles)) && (roles.indexOf("区直基层单位") >= 0 || roles.indexOf("经办") >= 0 || roles.indexOf("初核") >= 0 || roles.indexOf("复核") >= 0);
	}
	
	/** 是否地方人社 */
	public static boolean isDfrs(String roles) {
		return (!Strings.isNullOrEmpty(roles)) && (roles.indexOf("工资福利科") >= 0 || roles.indexOf("工资福利股") >= 0 || roles.indexOf("局领导") >= 0 || roles.indexOf("组织部") >= 0);
	}
}
