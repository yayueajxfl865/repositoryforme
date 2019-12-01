package com.okflow.modules.received.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import com.okflow.common.persistence.IdEntity;

/**
 * 角色等级权限Entity
 * 
 * @author xiaofanglin
 * @version 2019-11-26
 */
@Entity
@Table(name = "yb_grade")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Grade extends IdEntity<Grade> {

	private static final long serialVersionUID = 1L;

	private String yb_userid;// 易班ID

	private String role;// 角色,分为:管理员(admin)经办(handle)班长(monitor)会长(chairman)老师(teacher)(student)

	@Length(min = 0, max = 64)
	public String getYb_userid() {
		return yb_userid;
	}

	public void setYb_userid(String yb_userid) {
		this.yb_userid = yb_userid;
	}

	@Length(min = 0, max = 64)
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
