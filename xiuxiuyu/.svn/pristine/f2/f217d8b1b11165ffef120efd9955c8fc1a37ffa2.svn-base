package com.xiuxiuyu.jobs;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xiuxiuyu.beans.User;

public class TestJob {
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void execute(){
		System.out.println("扣除会员天数=======start========");
		Long start = System.currentTimeMillis();
		StringBuilder sql = new StringBuilder();
		sql.append(" from User where state=0 and roleId=2 and days>0");
		Integer pageSize = 100;
		Integer total = getTotal("select count(*) "+sql.toString());
		Integer totalPage = (total+pageSize-1)/pageSize;
		for(int page=1;page<=totalPage;page++){
			List<User> list = findUserListBySql(sql.toString(), page, pageSize);
			for(User user:list){
				try {
					updateUser(user);
				} catch (Exception e) {
					System.out.println("扣除会员天数失败(username=="+user.getUsername()+"):"+e.getMessage());
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Long end = System.currentTimeMillis();
		System.out.println("扣除会员天数=======end========耗时["+(end-start)+"]");
	}

	public Integer getTotal(String sql){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(sql);
		Long total = (Long) query.uniqueResult();
		Integer rows = Integer.valueOf(String.valueOf(total));
		session.close();
		return rows;
	}
	
	public List<User> findUserListBySql(String sql,Integer page,Integer pageSize){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(sql);
		query.setFirstResult((page-1)*pageSize);
		query.setMaxResults(pageSize);
		List<User> list = query.list();
		session.close();
		return list;
	}
	
	public void updateUser(User user){
		Session session = sessionFactory.openSession();
		Transaction Transaction = session.beginTransaction();
		session.saveOrUpdate(user);
		Transaction.commit();
		session.close();
	}
	
}
