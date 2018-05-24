package com.xiuxiuyu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xiuxiuyu.beans.Comment;

@Repository
public class CommentDaoImp implements ICommentDao{
	@Autowired
    private SessionFactory sessionFactory;
	public void updateComment(Comment comment) {
		sessionFactory.getCurrentSession().saveOrUpdate(comment);
	}

	public void deleteComment(Comment comment) {
		sessionFactory.getCurrentSession().delete(comment);
	}

	public Comment getCommentById(Integer id) {
		return sessionFactory.getCurrentSession().get(Comment.class, id);
	}

	public List<Comment> getCommentList(String sql, List params) {
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		if(params!=null&&!params.isEmpty()){
			for(int i=0;i<params.size();i++){
				query.setParameter(0, params.get(i));
			}
		}
		return query.list();
	}

}
