package com.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.bean.MusicScore;
import com.springboot.dao.MusicScoreDao;
import com.springboot.service.MusicScoreService;

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
	public int getMaxID() {
		try {
			return scoreDao.getMaxID();
		} catch(Exception e) {
			System.out.println("get max catch");
			return 0;
		}
	}
	
	@Override
	public boolean updateSupportCount(int id, boolean isAdd) {
		boolean result=true;
		if(isAdd) {
			try {
				scoreDao.addSupportCount(id);
			} catch(Exception e) {
				result = false;
			}
		} else {
			try {
				scoreDao.subSupportCount(id);
			} catch(Exception e) {
				result = false;
			}
		}
		return result;
	}
	
}
