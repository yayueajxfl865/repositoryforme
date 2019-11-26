package com.okflow.modules.received.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.okflow.common.persistence.IdEntity;

@Entity
@Table(name = "yb_user")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class YbUser extends IdEntity<YbUser> {
	private static final long serialVersionUID = 1L;
	private String yb_userid;// 易班id
	private String yb_realname;// 真实姓名
	private String yb_identity;// 用户身份
	private Claban claban;// 所属班级
	private String role;// 所属角色

	public YbUser() {
		super();
	}

	public YbUser(String yb_userid, String yb_realname, String yb_identity, Claban claban) {
		super();
		this.yb_userid = yb_userid;
		this.yb_realname = yb_realname;
		this.yb_identity = yb_identity;
		this.claban = claban;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getYb_userid() {
		return yb_userid;
	}

	public void setYb_userid(String yb_userid) {
		this.yb_userid = yb_userid;
	}

	public String getYb_realname() {
		return yb_realname;
	}

	public void setYb_realname(String yb_realname) {
		this.yb_realname = yb_realname;
	}

	public String getYb_identity() {
		return yb_identity;
	}

	public void setYb_identity(String yb_identity) {
		this.yb_identity = yb_identity;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "c_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	public Claban getClaban() {
		return claban;
	}

	public void setClaban(Claban claban) {
		this.claban = claban;
	}

}
