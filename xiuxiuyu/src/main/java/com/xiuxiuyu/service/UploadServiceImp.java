package com.xiuxiuyu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiuxiuyu.beans.UploadFile;
import com.xiuxiuyu.dao.IUploadDao;

@Service
@Transactional
public class UploadServiceImp implements IUploadService{
	@Autowired
    private IUploadDao uploadDao;
	public void save(UploadFile uploadFile) {
		uploadDao.save(uploadFile);
	}

	public UploadFile getUploadFileInfo(String path) {
		return uploadDao.getUploadFileInfo(path);
	}

}
