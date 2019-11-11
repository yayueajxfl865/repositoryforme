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
	private Producer producer;

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

}
