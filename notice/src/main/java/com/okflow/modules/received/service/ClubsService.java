package com.okflow.modules.received.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.okflow.modules.received.dao.ClubsDao;
import com.okflow.modules.received.entity.Clubs;

@Service
@Transactional(readOnly = true)
public class ClubsService {

	@Autowired
	private ClubsDao clubsDao;

	public List<Clubs> getClubsList(String clubsName) {
		return clubsDao.getClubsList(clubsName);
	}

	public List<Clubs> getAllClubsList() {
		return clubsDao.getAllClubsList();
	}

	public Clubs get(String id) {
		return clubsDao.get(id);
	}
}
