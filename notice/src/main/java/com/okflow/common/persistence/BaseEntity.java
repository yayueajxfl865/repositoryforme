package com.okflow.common.persistence;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * Entity支持类
 * 
 * @author xiaofanglin
 * @version
 */
@MappedSuperclass
public abstract class BaseEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 当前用户 */
	/** 用于搜索多个ID的时候设置搜索条件 */
	private String ids;

	@Transient
	public String getIds() {
		return ids;
	}

	@Transient
	public void setIds(String ids) {
		this.ids = ids;
	}

	public static final String SHOW = "1";
	public static final String HIDE = "0";
	public static final String YES = "1";
	public static final String NO = "0";
}
