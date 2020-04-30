package com.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.bean.Comment;
import com.springboot.dao.CommentDao;
import com.springboot.service.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentDao dao;

	@Override
	public boolean save(Comment comment) {
		try {
			dao.save(comment);
			return true;
		} catch(Exception e) {
			return false;
		}
		
	}


	@Override
	public List<Comment> getBy(long articleId) {
		try {
			return dao.getBy(articleId);
		} catch(Exception e) {
			return null;
		} 
	}
}
