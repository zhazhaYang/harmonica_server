package com.springboot.service;

import java.util.List;

import com.springboot.bean.User;

public interface UserService {
	/**
	 * 保存用户对象
	 * @param user
	 */
	void save(User user);
	
	/**
	 * 保存用户信息
	 * @return user
	 */
	
	User getUser(String account);
	
	Boolean isAccountExist(String account);
	
	Boolean isNameExist(String name);
	
	Boolean isNameExitsExceptAccount(String name, String account);
	
	void updateUser(User user);
	
	void changePwd(String pwd, String account);
	
	String getPwd(String account);
	
	/**
	 * 获取所有用户对象
	 * @return
	 */
	List<User> getUserList();
}
