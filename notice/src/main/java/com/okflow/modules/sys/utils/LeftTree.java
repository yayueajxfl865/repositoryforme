package com.okflow.modules.sys.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * easyUI 树形菜单封装
 * 
 * @author xiaofanglin
 * @version 
 */
public class LeftTree {
	public LeftTree() {
	}

	private String id;
	private String text;
	private String iconCls;
	private String state;
	private boolean checked;
	private Attributes attributes;
	private List<LeftTree> children = new ArrayList<LeftTree>();
	private String codeTableX;// 代码定义表索引维度x
	private String codeTableY;// 代码定义表索引维度y
	private String codeTableY2;// 代码定义索引维度y2
	private String isCrossValueFromCodeTable;// 是否对照数据也来自代码表
	private String isAreaSetDefine;// 是否是地方性标准
	private String isLastParent;// 是否是最后一个根节点

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

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Attributes getAttributes() {
		return attributes;
	}

	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	public List<LeftTree> getChildren() {
		return children;
	}

	public void setChildren(List<LeftTree> children) {
		this.children = children;
	}

	public String getCodeTableX() {
		return codeTableX;
	}

	public void setCodeTableX(String codeTableX) {
		this.codeTableX = codeTableX;
	}

	public String getCodeTableY() {
		return codeTableY;
	}

	public void setCodeTableY(String codeTableY) {
		this.codeTableY = codeTableY;
	}

	public String getIsCrossValueFromCodeTable() {
		return isCrossValueFromCodeTable;
	}

	public void setIsCrossValueFromCodeTable(String isCrossValueFromCodeTable) {
		this.isCrossValueFromCodeTable = isCrossValueFromCodeTable;
	}

	public String getCodeTableY2() {
		return codeTableY2;
	}

	public void setCodeTableY2(String codeTableY2) {
		this.codeTableY2 = codeTableY2;
	}

	public String getIsAreaSetDefine() {
		return isAreaSetDefine;
	}

	public void setIsAreaSetDefine(String isAreaSetDefine) {
		this.isAreaSetDefine = isAreaSetDefine;
	}

	public String getIsLastParent() {
		return isLastParent;
	}

	public void setIsLastParent(String isLastParent) {
		this.isLastParent = isLastParent;
	}
}
