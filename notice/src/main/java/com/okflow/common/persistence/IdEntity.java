package com.okflow.common.persistence;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import com.okflow.common.utils.IdGen;

/**
 * 数据Entity类
 * 
 * @author xiaofanglin
 * @version
 */
@MappedSuperclass
public abstract class IdEntity<T> extends DataEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 编号 */
	protected String id;
	/** 排序号 */
	protected Integer sortNum;

	public IdEntity() {
		super();
	}

	@PrePersist
	public void prePersist() {
		super.prePersist();
		this.id = IdGen.uuid();
	}

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

}
