package com.xiuxiuyu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xiuxiuyu.beans.Channel;
import com.xiuxiuyu.beans.Content;

@Repository
public class ContentDaoImp implements IContentDao{
	@Autowired
    private SessionFactory sessionFactory;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public Content getContentById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Content.class, id);
	}
	public List<Content> findContentList(String sql, List params ,Integer page ,Integer pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Query query=session.createQuery(sql);
		if(params!=null){
			for(int index=0;index<params.size();index++){
				query.setParameter(index, params.get(index));
			}
		}
		if(page!=null){
			query.setFirstResult((page-1)*pageSize);
			query.setMaxResults(pageSize);
		}
		List list=query.list();
		return list;
	}
	public Integer getTotal(String sql, List params) {
		Session session = sessionFactory.getCurrentSession();
		Query query=session.createQuery(sql);
		if(params!=null){
			for(int index=0;index<params.size();index++){
				query.setParameter(index, params.get(index));
			}
		}
		Long total =  (Long) query.uniqueResult();
		return total.intValue();
	}
	public void updateContent(Content content) {
		sessionFactory.getCurrentSession().saveOrUpdate(content);
	}
	public void deleteContent(Content content) {
		sessionFactory.getCurrentSession().delete(content);
	}
	public Content getContentByUrlAndId(String url, Integer id) {
		StringBuilder builder = new StringBuilder();
		builder.append("from Content where url='");
		builder.append(url);
		builder.append("' and id=");
		builder.append(id);
		Content content=(Content) sessionFactory.getCurrentSession().createQuery(builder.toString()).uniqueResult();
		return content;
	}
	public Content getContentBySQL(String sql) {
		return (Content) sessionFactory.getCurrentSession().createQuery(sql).setMaxResults(1).uniqueResult();
	}

}
