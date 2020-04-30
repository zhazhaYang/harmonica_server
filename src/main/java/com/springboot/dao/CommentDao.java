package com.springboot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.bean.Comment;

@Repository
public interface CommentDao extends CommonDao<Comment>{
	
	@Query(value = "select * from comment where article_id = ?1 order by id asc ", nativeQuery = true)
	List<Comment> getBy(long articleId);
}
