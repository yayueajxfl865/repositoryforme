package com.okflow.modules.sys.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形菜单封装
 * 
 * @author xiaofanglin
 * @version
 */
public class LeftAwesomeTree {
	private String id;
	private String name;
	private String link;
	private Icon icon;
	private List<LeftAwesomeTree> children = new ArrayList<LeftAwesomeTree>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public List<LeftAwesomeTree> getChildren() {
		return children;
	}

	public void setChildren(List<LeftAwesomeTree> children) {
		this.children = children;
	}
}
