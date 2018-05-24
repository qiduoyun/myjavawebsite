package com.xiuxiuyu.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xiuxiuyu.beans.Permission;

@Repository
public class PermissionDaoImp implements IPermissionDao{
	@Autowired
    private SessionFactory sessionFactory;
	public List<Permission> getSysPermissions() {
		List<Permission> list = sessionFactory.getCurrentSession().createQuery("from Permission").list();
		return list;
	}

}
