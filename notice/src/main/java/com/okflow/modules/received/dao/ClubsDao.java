package com.okflow.modules.received.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.okflow.common.persistence.BaseDao;
import com.okflow.common.persistence.Parameter;
import com.okflow.modules.received.entity.Clubs;

@Repository
public class ClubsDao extends BaseDao<Clubs> {

	public List<Clubs> getClubsList(String clubsName) {
		return find("from Clubs where clubsName=:p1", new Parameter(clubsName));
	}
}
