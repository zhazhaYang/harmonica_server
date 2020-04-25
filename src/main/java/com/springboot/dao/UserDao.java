package com.springboot.dao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.bean.User;

@Repository
public interface UserDao extends CommonDao<User> {
	User getByAccount(String account);
	User getByName(String name);
	
	@Query(value = "update user set name = ?1, sex = ?2, age = ?3, years = ?4, info = ?5 where account = ?6", nativeQuery = true)
	@Modifying
	void updateUserByAccount(String name, String sex, String age, String years, String info, String account);
	
	@Modifying
	@Query(value="update user set pwd = ?1 where account = ?2", nativeQuery = true)
	void changePwdByAccount(String pwd, String account);
	
//	@Query(value = "select name from user where account = ?", nativeQuery = true)
	
	
//	@Query("select pwd from user where account = ?1")
//	@Modifying
//	String getPwdByAccount(String account);
	
}
