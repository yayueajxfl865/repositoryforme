package com.okflow.modules.received.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.beust.jcommander.internal.Lists;
import com.okflow.common.persistence.IdEntity;

/**
 * 消息的生产者,即是生产者
 * 
 * @author xiaofanglin
 * @version 2019-10-20
 */

@Entity
@Table(name = "mq_producer")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Producer extends IdEntity<Producer> {
	private static final long serialVersionUID = 1L;
	private String yb_userid;// 易班ID
	private String yb_username;// 易班用户名
	private String yb_realname;// 真实姓名
	private List<Message> messageList = Lists.newArrayList();// 生产者消息
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
	@OneToMany(mappedBy = "producer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy(value = "id")
	@Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Message> getMessageList() {
		return messageList;
	}
	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}
	
	

}
