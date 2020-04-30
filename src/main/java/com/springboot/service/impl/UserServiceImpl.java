package com.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.bean.User;
import com.springboot.dao.UserDao;
import com.springboot.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Override
	public void save(User user) {
		userDao.save(user);
	}
	
	@Override
	public User getUser(String account) {
		return userDao.getByAccount(account);
	}
	
	@Override 
	public String getName(String account) {
		return userDao.getNameByAccount(account);
	}
	
	@Override
	public Boolean isAccountExist(String account) {
		if(userDao.getByAccount(account) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public Boolean isNameExist(String name) {
		if(userDao.getByName(name) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public Boolean isNameExitsExceptAccount(String name, String account) {
		return false;
	}
	
	@Override
	public void updateUser(User user) {
		userDao.updateUserByAccount(user.getName(), user.getSex(), user.getAge(), user.getYears(), user.getInfo(), user.getAccount());
	}
	
	@Override
	public void changePwd(String pwd, String account) {
		userDao.changePwdByAccount(pwd, account);
	}
	
	@Override 
	public String getPwd(String account) {
		return userDao.getByAccount(account).getPwd();
	}
	
	@Override
	public List<User> getUserList() {
		return userDao.findAll();
	}

	@Override
	public String getStudyAge(String account) {
		try {
			return userDao.getYearsByAccount(account);
		} catch(Exception e) {
			return null;
		}
		
	}

}
