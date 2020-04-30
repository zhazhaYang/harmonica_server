package com.springboot.service;

import com.springboot.bean.MusicScore;

public interface MusicScoreService {
	void save(MusicScore score);
	MusicScore getByID(long id);
	boolean checkExistsBy(String owner, String name);
	int getMaxID();
	boolean updateSupportCount(int id, boolean isAdd);
}