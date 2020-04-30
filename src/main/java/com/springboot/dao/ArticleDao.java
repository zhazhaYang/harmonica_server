package com.springboot.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.bean.Article;

@Repository
public interface ArticleDao extends CommonDao<Article> {
	
	Article getById(long id);
	
	@Query(value="select title from article where id = ?1", nativeQuery = true)
	String getTitle(long id);
	
	@Query(value="select id from article order by id desc limit 0,1", nativeQuery = true)
	int getMaxID();
	
	@Query(value="select count(*) from article", nativeQuery = true)
	int getAmount();
	
	@Query(value = "update article set supports = supports + 1 where id = ?1", nativeQuery = true)
	@Modifying
	void addSupport(long id);
	
	@Query(value = "update article set supports = supports - 1 where id = ?1 and supports != 0", nativeQuery = true)
	@Modifying
	void subSupport(long id);
}