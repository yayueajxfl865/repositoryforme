package com.okflow.modules.sys.utils;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * ACE树形菜单封装
 * 
 * @author xiaofanglin
 * @version 
 */
public class AceTree {
	public AceTree() {
	}

	private String id;
	private String text;
	private String icon;
	private String url;
	private String title;
	private List<AceTree> menus = Lists.newArrayList();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<AceTree> getMenus() {
		return menus;
	}

	public void setMenus(List<AceTree> menus) {
		this.menus = menus;
	}
}
