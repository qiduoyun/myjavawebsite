package com.xiuxiuyu.dao;

import java.util.List;

import com.xiuxiuyu.beans.Permission;

public interface IPermissionDao {
	/**
	 * ��ȡϵͳȨ����Ϣ
	 * @return
	 */
    public List<Permission> getSysPermissions();
}
