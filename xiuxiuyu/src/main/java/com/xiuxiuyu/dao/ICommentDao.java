package com.xiuxiuyu.dao;

import java.util.List;

import com.xiuxiuyu.beans.Comment;

public interface ICommentDao {
	/**
	 * 更新评论信息
	 * @param comment
	 */
public void updateComment(Comment comment);
/**
 * 删除评论
 * @param comment
 */
public void deleteComment(Comment comment);
/**
 * 根据ID查询评论信息
 * @param id
 * @return
 */
public Comment getCommentById(Integer id);
/**
 * 查询评论列表信息
 * @param sql
 * @param params
 * @return
 */
public List<Comment> getCommentList(String sql,List params);
}
