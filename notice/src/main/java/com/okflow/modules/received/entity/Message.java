package com.okflow.modules.received.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
import org.hibernate.validator.constraints.Length;

import com.beust.jcommander.internal.Lists;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.okflow.common.persistence.IdEntity;

/**
 * 消息Entiry
 * 
 * @author xiaofanglin
 * @version 2019-10-20
 */

@Entity
@Table(name = "mq_message")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Message extends IdEntity<Message> {
	private static final long serialVersionUID = 1L;
	private String content;// 消息内容
	private Producer producer;//消息生产者
	private List<Consumer> conList = Lists.newArrayList();// 一条消息有多个消费者

	@Length(min = 0, max = 1000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "producer_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	@OneToMany(mappedBy = "message", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy(value = "id")
	@Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Consumer> getConList() {
		return conList;
	}

	public void setConList(List<Consumer> conList) {
		this.conList = conList;
	}

}
