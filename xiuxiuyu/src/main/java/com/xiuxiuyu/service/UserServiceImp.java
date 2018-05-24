package com.xiuxiuyu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiuxiuyu.beans.Content;
import com.xiuxiuyu.beans.User;
import com.xiuxiuyu.dao.IUserDao;

import com.xiuxiuyu.util.PageInfo;
import com.xiuxiuyu.util.StringUtils;

@Service
@Transactional
public class UserServiceImp implements IUserService{
	@Autowired
    private IUserDao userDao;
	
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}
	public void SaveUser(User user) {
		// TODO Auto-generated method stub
		userDao.SaveUser(user);
		
	}
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		return userDao.getUserById(id);
	}
	
	
	
	
	public PageInfo<User> findUserList(PageInfo<User> pageInfo, User user) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
				StringBuffer sql = new StringBuffer();
				List params = new ArrayList();
				sql.append("from User where 1=1");
				if(user!=null&&user.getId()!=null){
					sql.append(" and id=?");
					params.add(user.getId());
				}
				if(user!=null&&!(StringUtils.isEmpty(user.getUsername()))){
					
					sql.append(" and username=?");
					params.add(user.getUsername());
				}
                if(user!=null&&!(StringUtils.isEmpty(user.getTelphone()))){
					
					sql.append(" and telphone=?");
					params.add(user.getTelphone());
				}
				Integer total = userDao.getTotal("select count(*) "+sql.toString(), params);
				sql.append(" order by id desc");
				
				List<User> list=userDao.findUserList(sql.toString(), params, pageInfo.getPage(), pageInfo.getPageSize());
				pageInfo.setResultList(list);
				pageInfo.setRows(total);
				return pageInfo;
	}

}
