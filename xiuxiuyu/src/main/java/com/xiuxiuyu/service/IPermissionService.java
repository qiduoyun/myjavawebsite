package com.xiuxiuyu.service;

import java.util.List;

import com.xiuxiuyu.beans.Permission;

public interface IPermissionService {
	/**
	 * 获取系统权限信息
	 * @return
	 */
    public List<Permission> getSysPermissions();
}
