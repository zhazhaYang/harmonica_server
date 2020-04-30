package com.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.springboot.bean.Comment;
import com.springboot.service.CommentService;
import com.springboot.service.UserService;

@Controller
public class CommnetController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "comment/get", method = RequestMethod.GET)
	@ResponseBody
	public String getComments(@RequestParam(value = "articleId", required = false) long articleId) {
		List<Comment> coms = commentService.getBy(articleId);
		if(coms == null) coms = new ArrayList<Comment>(); 
		List<String> jsons = new ArrayList<String>();
		for(int i = 0; i < coms.size(); i++) {
			Comment com = coms.get(i);
			JSONObject json = new JSONObject();
			String name = userService.getName(com.getAccount());
			System.out.println(name);
			String replyName = userService.getName(com.getReplyAccount());
			System.out.println(replyName);
			json.put("commentID", com.getID());
			json.put("name", name);
			json.put("account", com.getAccount());
			json.put("content", com.getContent());
			json.put("replyName", replyName);
			json.put("onComment", com.getOnComment());
			jsons.add(json.toJSONString());
			System.out.println(json.toJSONString());
		}

		return jsons.toString();
	}
	
	@RequestMapping(value = "comment/save", method = RequestMethod.POST)
	@ResponseBody
	public boolean saveComment(@RequestBody Comment comment) {
		return commentService.save(comment);
	}
}
