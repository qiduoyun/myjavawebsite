package com.xiuxiuyu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiuxiuyu.beans.Comment;
import com.xiuxiuyu.dao.ICommentDao;

@Service
@Transactional
public class CommentServiceImp implements ICommentService {
	@Autowired
    private ICommentDao commentDao;
	public void updateComment(Comment comment) {
		commentDao.updateComment(comment);
	}

	public void deleteComment(Comment comment) {
		commentDao.deleteComment(comment);
	}

	public Comment getCommentById(Integer id) {
		return commentDao.getCommentById(id);
	}

	public List<Comment> getCommentList(String sql, List params) {
		return commentDao.getCommentList(sql, params);
	}

}
