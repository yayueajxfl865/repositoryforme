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
@Table(name = "mq_imessage")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Imessage extends IdEntity<Imessage> {
	private static final long serialVersionUID = 1L;
	private Producer producer;// 消息生产者
	private List<Consumer> conList = Lists.newArrayList();// 一条消息有多个消费者
	private String theme;// 消息主题
	private String content;// 消息内容
	private String state;// 消息类型
	private String fszt;// 发送状态
	private String recall;// 撤回操作
	private String rever;// 接收者简介

	@Length(min = 0, max = 100)
	public String getRever() {
		return rever;
	}

	public void setRever(String rever) {
		this.rever = rever;
	}

	@Length(min = 0, max = 2)
	public String getRecall() {
		return recall;
	}

	public void setRecall(String recall) {
		this.recall = recall;
	}

	@Length(min = 0, max = 2)
	public String getFszt() {
		return fszt;
	}

	public void setFszt(String fszt) {
		this.fszt = fszt;
	}

	@Length(min = 0, max = 2)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Length(min = 0, max = 100)
	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

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

	@OneToMany(mappedBy = "imessage", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

	@Override
	public String toString() {
		return "Message [theme=" + theme + ", content=" + content + "]";
	}

}
