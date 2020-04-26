package com.springboot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.bean.MusicScore;
import com.springboot.bean.User;

@Repository
public interface MusicScoreDao extends CommonDao<MusicScore> {
//	User getByAccount(String account);
//	User getByName(String name);
//	
//	@Query(value = "update user set name = ?1, sex = ?2, age = ?3, years = ?4, info = ?5 where account = ?6", nativeQuery = true)
//	@Modifying
//	void updateUserByAccount(String name, String sex, String age, String years, String info, String account);
//	
//	@Modifying
//	@Query(value="update user set pwd = ?1 where account = ?2", nativeQuery = true)
//	void changePwdByAccount(String pwd, String account);
	MusicScore getById(long id);
	
	@Modifying
	@Query(value="select * from music_score where owner_account = ?1 and image_name = ?2", nativeQuery = true)
	List<MusicScore> getByAccountAndName(String owner, String name);
	
	@Modifying
	@Query(value="select id from music_score order by id desc limit 0,1", nativeQuery = true)
	long getMaxID();
	
}
