package com.xiuxiuyu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xiuxiuyu.beans.User;

@Repository
public class UserDaoImp implements IUserDao{
	@Autowired
    private SessionFactory sessionFactory;
	public User getUserByUsername(String username) {
		Query query = sessionFactory.getCurrentSession().createQuery("from User where username=?");
		query.setString(0, username);
		return (User) query.uniqueResult();
	}
	
	public void SaveUser(User user) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}

	public User getUserById(Integer id) {
		Query query =sessionFactory.getCurrentSession().createQuery("from User where id=?");
		query.setInteger(0, id);
		return (User) query.uniqueResult();
		// TODO Auto-generated method stub
		
	}

	public Integer getTotal(String sql, List params) {
		// TODO Auto-generated method stub
		Session session= sessionFactory.getCurrentSession();
		Query query=  session.createQuery(sql);
		if(params!=null){
			for(int index=0;index<params.size();index++){
				
				query.setParameter(index, params.get(index));
			}
				
		}
		Long total=(Long) query.uniqueResult();
		return total.intValue();
	}

	public List<User> findUserList(String sql, List params, Integer page, Integer pageSize) {
		
		// TODO Auto-generated method stub
		
		Session session=sessionFactory.getCurrentSession();
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
		return query.list();
		
	}

}
