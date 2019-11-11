package com.okflow.common.filter;

import com.okflow.middleware.ehcache.CacheUtils;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;

/**
 * 页面高速缓存过滤器(使用ehcache缓存页,缓存整个页面 )
 * 
 * @author xiaofanglin
 * @version 2019-10-04
 */
public class PageCachingFilter extends SimplePageCachingFilter {
	@Override
	protected CacheManager getCacheManager() {
		return CacheUtils.getCacheManager();
	}
}
