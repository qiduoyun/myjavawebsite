package com.xiuxiuyu.service;

import java.util.List;


import com.xiuxiuyu.beans.Content;
import com.xiuxiuyu.beans.User;
import com.xiuxiuyu.util.PageInfo;

public interface IUserService {
	/**
	 * �����û�����ȡ�û���Ϣ
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username);
	/**
	 * ���û�ע��
	 */
	public void SaveUser(User user);
	public User getUserById(Integer id);
	public PageInfo<User> findUserList(PageInfo<User> pageInfo,User user);
	//public List<User> findUserList(String sql,List params,Integer page,Integer pageSize);
}
