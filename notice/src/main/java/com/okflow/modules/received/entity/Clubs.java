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
import org.hibernate.validator.constraints.Length;

import com.beust.jcommander.internal.Lists;
import com.okflow.common.persistence.IdEntity;

/**
 * 社团Entity
 * 
 * @author xiaofanglin
 * @version 2019-11-27
 */

@Entity
@Table(name = "mq_clubs")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Clubs extends IdEntity<Clubs> {
	private static final long serialVersionUID = 1L;

	private String clubsName;// 社团名称

	private List<YbUser> ybUserList = Lists.newArrayList();

	@Length(min = 0, max = 30)
	public String getClubsName() {
		return clubsName;
	}

	public void setClubsName(String clubsName) {
		this.clubsName = clubsName;
	}

	@OneToMany(mappedBy = "clubs", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy(value = "id")
	@Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<YbUser> getYbUserList() {
		return ybUserList;
	}

	public void setYbUserList(List<YbUser> ybUserList) {
		this.ybUserList = ybUserList;
	}

}
