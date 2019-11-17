package com.okflow.modules.received.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
import org.hibernate.validator.constraints.Length;

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
	private YbUser ybUser;// 易班用户
	private List<Imessage> messageList = Lists.newArrayList();// 生产者消息

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "yb_id")
	public YbUser getYbUser() {
		return ybUser;
	}

	public void setYbUser(YbUser ybUser) {
		this.ybUser = ybUser;
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

	@OneToMany(mappedBy = "producer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy(value = "id")
	@Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Imessage> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<Imessage> messageList) {
		this.messageList = messageList;
	}

}
