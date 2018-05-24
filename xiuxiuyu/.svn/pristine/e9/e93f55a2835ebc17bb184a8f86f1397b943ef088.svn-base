package com.xiuxiuyu.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xiuxiuyu.beans.UploadFile;

@Repository
public class UploadDaoImp implements IUploadDao{
	@Autowired
    private SessionFactory sessionFactory;
	public void save(UploadFile uploadFile) {
		sessionFactory.getCurrentSession().save(uploadFile);
	}

	public UploadFile getUploadFileInfo(String path) {
		return sessionFactory.getCurrentSession().get(UploadFile.class, path);
	}

}
