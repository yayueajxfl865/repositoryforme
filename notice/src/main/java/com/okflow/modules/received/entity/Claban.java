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

import com.beust.jcommander.internal.Lists;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.okflow.common.persistence.IdEntity;

/**
 * 班别Entity
 * 
 * @author xiaofanglin
 *
 */
@Entity
@Table(name = "yb_claban")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Claban extends IdEntity<Claban> {
	private static final long serialVersionUID = 1L;

	private String name;// 班级名称
	private String code;// 编号
	private Tie tie;// 所属系别
	private List<YbUser> ybList = Lists.newArrayList();

	public Claban() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Claban(String name, String code, Tie tie, List<YbUser> ybList) {
		super();
		this.name = name;
		this.code = code;
		this.tie = tie;
		this.ybList = ybList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "t_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	public Tie getTie() {
		return tie;
	}

	public void setTie(Tie tie) {
		this.tie = tie;
	}

	@OneToMany(mappedBy = "claban", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy(value = "id")
	@Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<YbUser> getYbList() {
		return ybList;
	}

	public void setYbList(List<YbUser> ybList) {
		this.ybList = ybList;
	}

}
