package com.xiuxiuyu.service;

import java.util.List;

import com.xiuxiuyu.beans.Permission;

public interface IPermissionService {
	/**
	 * ��ȡϵͳȨ����Ϣ
	 * @return
	 */
    public List<Permission> getSysPermissions();
}
