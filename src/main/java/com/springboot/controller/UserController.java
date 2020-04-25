package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springboot.bean.User;
import com.springboot.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/register")
	@ResponseBody
	public String register(@RequestBody User user) {
		if(userService.isAccountExist(user.getAccount())) {
			return "该账户号已存在，请重新输入";
		}
		if(userService.isNameExist(user.getName())) {
			return "该昵称称已存在，请重新输入";
		}
		String result = "注册成功!";
		try {
			userService.save(user);
		} catch(Exception e) {
			result = "注册失败，请稍后再尝试";
		} finally {}
		
		return result;
	}
	
	@RequestMapping("/user/check")
	@ResponseBody
	public boolean checkPwd(@RequestBody User user) {
		String enter = user.getPwd();
		String correct = "";
		try {
			correct = userService.getPwd(user.getAccount());
		}catch(Exception e) {
			correct = "";
		} finally {
		}
		System.out.println(correct);
		if(correct != null && enter.equals(correct)) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(value = "/user/get", method= RequestMethod.GET)
	@ResponseBody
	public String getByAccount(@RequestParam String account) {
		User user;;
		try {
			user = userService.getUser(account); 
		} catch(Exception e) {
			user = new User();
		} finally {}
		return JSON.toJSONString(user);
	}
	
	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@RequestBody User user) {
		String result = "更新成功";
		try {
			userService.updateUser(user);
		} catch(Exception e) {
			result = "更新失败 \n 可能该昵称已被占用，请更换再尝试";
		} finally {

		}
		return result;
	}
	
	@RequestMapping(value = "/user/password", method = RequestMethod.POST)
	@ResponseBody
	public Boolean changePwd(@RequestBody User user) {
		Boolean result = true;
		try {
			userService.changePwd(user.getPwd(), user.getAccount());
		} catch(Exception e) {
			result = false;
		} finally {
			
		}
		return true;
	}
	
	@RequestMapping("/userList")
	public String userList(Model model) {
		List<User> userList = userService.getUserList();
		model.addAttribute("userList", userList);
		return "List";
	}

}
