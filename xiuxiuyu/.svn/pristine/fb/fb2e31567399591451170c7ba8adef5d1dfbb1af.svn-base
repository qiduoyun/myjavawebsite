package com.xiuxiuyu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xiuxiuyu.beans.PayOrder;

@Repository
public class OrderDaoImp implements IOrderDao {
	@Autowired
    private SessionFactory sessionFactory;
	public void updateOrder(PayOrder order) {
		sessionFactory.getCurrentSession().saveOrUpdate(order);
	}
	public List<PayOrder> findPayOrderList(String sql, List param, Integer page, Integer pageSize) {
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		if(param!=null&&!param.isEmpty()){
			for(int i=0;i<param.size();i++){
				query.setParameter(i, param.get(i));
			}
		}
		if(page!=null&&page>0){
			query.setFirstResult((page-1)*pageSize);
			query.setMaxResults(pageSize);
		}
		return query.list();
	}
	public PayOrder getPayOrderByOrderId(String orderId) {
		String sql = "from PayOrder where orderId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter(0, orderId);
		query.setMaxResults(1);
		return (PayOrder) query.uniqueResult();
	}
	public Integer getTotalCount(String sql, List param) {
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		if(param!=null&&!param.isEmpty()){
			for(int i=0;i<param.size();i++){
				query.setParameter(i, param.get(i));
			}
		}
		Long total = (Long) query.uniqueResult();
		return Integer.valueOf(String.valueOf(total));
	}

}
