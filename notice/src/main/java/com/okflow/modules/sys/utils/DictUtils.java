package com.okflow.modules.sys.utils;

import java.util.List;

import java.util.Map;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.okflow.common.utils.SpringContextHolder;
import com.okflow.middleware.ehcache.CacheUtils;
import com.okflow.modules.sys.dao.DictDao;
import com.okflow.modules.sys.entity.Dict;

/**
 * 数据字典工具类
 * 
 * @author luoshanxing
 * @version 2015-12-26
 */
public class DictUtils {
	private static DictDao dictDao = SpringContextHolder.getBean(DictDao.class);
	private static final String AND = "并";
	public static final String CACHE_DICT_MAP = "dictMap";

	public static String getDictLabel(String value, String type, String defaultValue) {
		if ((!Strings.isNullOrEmpty(type)) && (!Strings.isNullOrEmpty(value))) {
			if (type.equals("gzbdyy") && (value.trim().length() > 4)) {
				StringBuffer v = new StringBuffer();
				for (Dict dict : getDictList(type)) {
					if (type.equals(dict.getType()) && value.equals(dict.getValue())) {
						return dict.getLabel();
					}
					if (type.equals(dict.getType()) && value.substring(0, 4).equals(dict.getValue())) {
						v.append(AND);
						v.append(dict.getLabel());
					}
					if (type.equals(dict.getType()) && (value.length() >= 8) && value.substring(4, 8).equals(dict.getValue())) {
						v.append(AND);
						v.append(dict.getLabel());
					}
					if (value.length() == 12) {
						if (type.equals(dict.getType()) && value.substring(8, 12).equals(dict.getValue())) {
							v.append(AND);
							v.append(dict.getLabel());
						}
					}
					if (value.length() == 16) {
						if (type.equals(dict.getType()) && value.substring(12, 16).equals(dict.getValue())) {
							v.append(AND);
							v.append(dict.getLabel());
						}
					}
					if (value.length() == 20) {
						String temp = getDictLabel(value.substring(0, 10), type, "") + AND + getDictLabel(value.substring(10, 20), type, "");
						if (temp.startsWith(AND)) {
							return temp.substring(1, temp.length());
						}
						else {
							return temp;
						}
					}
				}
				String string = v.toString();
				if (string.startsWith(AND)) {
					return string.substring(1, string.length());
				}
				else {
					return string;
				}
			}
			else {
				for (Dict dict : getDictList(type)) {
					if (type.equals(dict.getType()) && value.equals(dict.getValue())) {
						return dict.getLabel();
					}
				}
			}
		}
		return defaultValue;
	}

	public static String getDictValue(String label, String type, String defaultLabel) {
		if ((!Strings.isNullOrEmpty(type)) && (!Strings.isNullOrEmpty(label))) {
			for (Dict dict : getDictList(type)) {
				if (type.equals(dict.getType()) && label.equals(dict.getLabel())) {
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}

	public static List<Dict> getDictList(String type) {
		@SuppressWarnings("unchecked")
		Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>) CacheUtils.get(CACHE_DICT_MAP);
		if (dictMap == null) {
			dictMap = Maps.newHashMap();
			for (Dict dict : dictDao.findAllList()) {
				List<Dict> dictList = dictMap.get(dict.getType());
				if (dictList != null) {
					dictList.add(dict);
				}
				else {
					dictMap.put(dict.getType(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_DICT_MAP, dictMap);
		}
		List<Dict> dictList = dictMap.get(type);
		if (dictList == null) {
			dictList = Lists.newArrayList();
		}
		return dictList;
	}

	/**
	 * 获取进入单位方式的标签
	 * 
	 * @param type 取机关(01)或事业(02)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Dict> getDictListOfjrdwfs(String type) {
		Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>) CacheUtils.get(CACHE_DICT_MAP);
		List<Dict> dictList = null;
		if (dictMap == null) {
			dictList = dictDao.findByType("xzfs");
		}
		else {
			dictList = dictMap.get("xzfs");
		}
		List<Dict> returnList = Lists.newArrayList();
		if ("jg".equals(type)) {
			for (Dict dic : dictList) {
				if (dic.getDescription().startsWith("机关")) {
					returnList.add(dic);
				}
			}
		}
		else if ("sy".equals(type)) {
			for (Dict dic : dictList) {
				if (dic.getDescription().startsWith("事业")) {
					returnList.add(dic);
				}
			}
		}
		return returnList;
	}
}