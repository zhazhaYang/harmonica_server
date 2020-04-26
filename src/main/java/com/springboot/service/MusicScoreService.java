package com.springboot.service;

import java.util.List;

import com.springboot.bean.MusicScore;
import com.springboot.bean.User;

public interface MusicScoreService {
	void save(MusicScore score);
	MusicScore getByID(long id);
	boolean checkExistsBy(String owner, String name);
	long getMaxID();
}