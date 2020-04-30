package com.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.bean.Article;
import com.springboot.dao.ArticleDao;
import com.springboot.service.ArticleService;

@Service
@Transactional

public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao dao;
	
	@Override
	public boolean saveArticle(Article article) {
		try {
			dao.save(article);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public int getMaxID() {
		try {
			return dao.getMaxID();
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public Article getByID(long id) {
		try {
			return dao.getById(id);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public int getAmount() {
		try {
			return dao.getAmount();
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public String getTitle(long id) {
		try {
			return dao.getTitle(id);
		}catch(Exception e) {
			return "";
		}
	}

	@Override
	public boolean updateSupport(long id, boolean isAdd) {
		if(isAdd) {
			try {
				dao.addSupport(id);
				return true;
			} catch(Exception e) {
				return false;
			}
		} else {
			try {
				dao.subSupport(id);
				return true;
			} catch(Exception e) {
				return false;
			}
		}
	}

}
