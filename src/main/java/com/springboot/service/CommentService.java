package com.springboot.service;

import java.util.List;

import com.springboot.bean.Comment;

public interface CommentService {
	List<Comment> getBy(long articleId);
	boolean save(Comment comment);
}
