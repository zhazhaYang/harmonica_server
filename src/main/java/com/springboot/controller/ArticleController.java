package com.springboot.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springboot.bean.Article;
import com.springboot.service.ArticleService;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService service;
	
	@RequestMapping(value="/article/publish", method = RequestMethod.POST)
	@ResponseBody
	boolean saveArticle(@RequestBody Article article) {
		if(article != null) {
			article.setSupports(0);
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			article.setTime(df.format(new Date()));
		}
		try {
			service.saveArticle(article);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	@RequestMapping(value = "/article/getArticle", method = RequestMethod.GET)
	@ResponseBody
	String getArticle(@RequestParam(value = "id", required = false) long id) {
		return JSON.toJSONString(service.getByID(id));
	}
	
	@RequestMapping(value = "/article/getNext", method = RequestMethod.GET) 
	@ResponseBody
	String getNextArticles(@RequestParam(value = "id", required = false) long id, @RequestParam(value = "amount", required = false) long amount) {
		long max = service.getMaxID();
		long current = id;
		long length = 1;
		JSONObject json = new JSONObject();
		while(current <= max && length <= amount) {
			String title = service.getTitle(current);
			if(title != null) {
				json.put(title, current);
				length ++;
			}
			current ++;
		}
		System.out.println(json.toString());
		return json.toJSONString();
	}
	
	@RequestMapping(value = "/article/getPrevious", method = RequestMethod.GET) 
	@ResponseBody
	String getPreviousArticles(@RequestParam(value = "id", required = false) long id, @RequestParam(value = "amount", required = false) long amount) {
		long current = id;
		long length = 1;
		JSONObject json = new JSONObject();
		while(current > 0 && length <= amount) {
			String title = service.getTitle(current);
			if(title != null) {
				json.put(title, current);
				length ++;
			}
			current --;
		}
		System.out.println(json.toString());
		return json.toJSONString();
	}
	
	@RequestMapping(value = "/article/amount", method = RequestMethod.GET)
	@ResponseBody
	int getAmount() {
		return service.getAmount();
	}
	
	@RequestMapping(value = "/article/updateSupport", method = RequestMethod.GET)
	@ResponseBody
	boolean updateSupport(@RequestParam(value = "id", required = false) long id, 
			@RequestParam(value = "isAdd", required = false) boolean isAdd) {
		return service.updateSupport(id, isAdd);
	}

}
