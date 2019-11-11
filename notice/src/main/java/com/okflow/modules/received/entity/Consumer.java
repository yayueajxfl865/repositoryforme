package com.okflow.modules.received.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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

	public String getYb_userid() {
		return yb_userid;
	}

	public void setYb_userid(String yb_userid) {
		this.yb_userid = yb_userid;
	}

	public String getYb_username() {
		return yb_username;
	}

	public void setYb_username(String yb_username) {
		this.yb_username = yb_username;
	}

	public String getYb_realname() {
		return yb_realname;
	}

	public void setYb_realname(String yb_realname) {
		this.yb_realname = yb_realname;
	}

}
