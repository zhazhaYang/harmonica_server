package com.springboot.service;

import com.springboot.bean.Article;

public interface ArticleService {
	boolean saveArticle(Article article);
	int getMaxID();
	int getAmount();
	String getTitle(long id);
	Article getByID(long id);
	boolean updateSupport(long id, boolean isAdd);
}
