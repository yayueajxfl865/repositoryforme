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
 * 系别Entity
 * 
 * @author xiaofanglin
 *
 */
@Entity
@Table(name = "yb_tie")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tie extends IdEntity<Tie> {
	private static final long serialVersionUID = 1L;
	private String name;// 系名
	private String number;// 系编号
	private List<Claban> claList = Lists.newArrayList();// 对应班级

	public Tie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tie(String name, String number, List<Claban> claList) {
		super();
		this.name = name;
		this.number = number;
		this.claList = claList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@OneToMany(mappedBy = "tie", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy(value = "id")
	@Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Claban> getClaList() {
		return claList;
	}

	public void setClaList(List<Claban> claList) {
		this.claList = claList;
	}

}
