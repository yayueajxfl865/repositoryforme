package com.okflow.modules.received.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.okflow.common.persistence.IdEntity;

/**
 * 消息的接收者,即消费者
 * 
 * @author xiaofanglin
 * @version 2019-10-20
 */
@Entity
@Table(name = "mq_consumer")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Consumer extends IdEntity<Consumer> {

	private static final long serialVersionUID = 1L;
	private String yb_userid;// 易班ID
	private String yb_username;// 易班用户名
	private String yb_realname;// 真实姓名
	private Imessage imessage;// 消息
	private String status;// 查看状态(1为已查看状态)
	private String state;// 是否撤回
	private YbUser ybUser;// 易班用户

	@Length(min = 0, max = 2)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "yb_id")
	public YbUser getYbUser() {
		return ybUser;
	}

	public void setYbUser(YbUser ybUser) {
		this.ybUser = ybUser;
	}

	@Length(min = 0, max = 1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "imessage_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	public Imessage getImessage() {
		return imessage;
	}

	public void setImessage(Imessage imessage) {
		this.imessage = imessage;
	}

	@Length(min = 0, max = 128)
	public String getYb_userid() {
		return yb_userid;
	}

	public void setYb_userid(String yb_userid) {
		this.yb_userid = yb_userid;
	}

	@Length(min = 0, max = 64)
	public String getYb_username() {
		return yb_username;
	}

	public void setYb_username(String yb_username) {
		this.yb_username = yb_username;
	}

	@Length(min = 0, max = 20)
	public String getYb_realname() {
		return yb_realname;
	}

	public void setYb_realname(String yb_realname) {
		this.yb_realname = yb_realname;
	}

}
