package com.xiuxiuyu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiuxiuyu.beans.Permission;
import com.xiuxiuyu.dao.IPermissionDao;

@Service
@Transactional
public class PermissionServiceImp implements IPermissionService{
	@Autowired
    private IPermissionDao permissionDao;
	public List<Permission> getSysPermissions() {
		return permissionDao.getSysPermissions();
	}

}
