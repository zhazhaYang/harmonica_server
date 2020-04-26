package com.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.bean.MusicScore;
import com.springboot.bean.User;
import com.springboot.dao.MusicScoreDao;
import com.springboot.dao.UserDao;
import com.springboot.service.MusicScoreService;
import com.springboot.service.UserService;

@Service
@Transactional

public class MusicScoreServiceImpl implements MusicScoreService {
	@Autowired
	private MusicScoreDao scoreDao;

	@Override
	public void save(MusicScore score) {
		scoreDao.save(score);
	}
	
	@Override
	public MusicScore getByID(long id) {
		return scoreDao.getById(id);
	}
	
	@Override
	public boolean checkExistsBy(String owner, String name) {
		List<MusicScore> list = null;
		System.out.println(owner + "  " + name);
		try {
			list = scoreDao.getByAccountAndName(owner, name);
		} catch(Exception e) {}
		
		if( list == null || list.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public long getMaxID() {
		try {
			return scoreDao.getMaxID();
		} catch(Exception e) {
			return 0;
		}
	}
	
}
